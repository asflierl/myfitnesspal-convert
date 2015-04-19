* Load data from http://myfitnesspal.com/reports/results/progress/1/2000.json (where 2000 means the number of days from today backwards) and save the JSON output to a file.
* From the project root directory run: 
```
sbt "run /path/to/your/file.json"
```
* The program will convert the JSON data to a format spreadsheet apps can import. The output is biased towards Apple's Numbers (i.e. semicolon separated, decimal separator depends on system default locale).