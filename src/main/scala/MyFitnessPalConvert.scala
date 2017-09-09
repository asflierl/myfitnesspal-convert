package eu.flierl

import java.nio.file.Path
import java.time.LocalDate

import cats.implicits._
import io.circe.generic.auto._
import io.circe.parser._

import scala.util.Try
import scala.{Either => \/}

object MyFitnessPalConvert extends App {
  args match {
    case Array(the) =>
      if (the.path.exists && the.path.isAFile) convert(the path).fold(s => println(s"Error: $s"), p => println(s"Output saved to: $p"))
      else println(s"No such file: ${the path}")
    case _          => println("Please provide the path to a JSON file as the first and only argument.")
  }

  def convert(inputPath: Path): String \/ Path = {
    val json = inputPath.slurped

    for {
      dataPoints <- decode[Report](json).leftMap("parse error: " +).map(_ data)
      relevant    = dataPoints dropWhile (_.total == 0d)
      plausible   = reconstructDate(relevant)
      formatted   = reformat(plausible)
      outputPath <- write(formatted, inputPath withExtension "csv")
    } yield outputPath
  }

  def reconstructDate(dataPoints: List[DataPoint]): List[DataPoint] =
    dataPoints.reverse.foldLeft((currentYear, 12, List.empty[DataPoint])) {
      case ((previousYear, previousMonth, accu), DataPoint(dateAs(monthStr, dayStr), total)) =>
        val (month, day) = (monthStr.toInt, dayStr.toInt)
        val year = if (month > previousMonth) previousYear - 1 else previousYear
        val date = LocalDate.of(year, month, day).toString
        (year, month.toInt, DataPoint(date, total) :: accu)
    }._3

  def reformat(dataPoints: List[DataPoint]) = dataPoints.map(p => s""""${p.date}";"${f"${p.total}%.1f"}"""")

  def write(theFancyData: List[String], destination: Path): String \/ Path = Try {
    if (destination.exists && destination.isAFile) destination.delete
    destination willNowContain theFancyData
  }.toEither leftMap (_ getMessage)

  lazy val dateAs = "(\\d+)/(\\d+)".r
  lazy val currentYear = LocalDate.now.getYear
}

case class Report(data: List[DataPoint])
case class DataPoint(date: String, total: Double)