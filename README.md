# Quantcast Coding Task

## Project Structure
* MostActiveCookie - Class that parses the data and outputs the most active cookies.

* MostActiveCookieTest - Unit tests for MostActiveCookie, primarily testing edge cases and making sure sorting of time stamps is working as expected.

* Main - The driver code that parses the command line arguments and initializes MostActiveCookie in order to read the inputted file and parse the data.


## How to Run

* Go to the src directory in the downloaded folder and open Main.java. Change the file path in readFile to match the absolute path of the data folder on your system so the command line is able to retrieve the specified CSV file from the data folder.

* In your terminal cd into the src directory and compile the program with the following command:

``` java
javac Main.java
```

* Then run the following command passing in a CSV filename and date arguments, eg: filename: cookie_log.csv, date: 2018-12-08:
``` java
java Main cookie_log.csv -d 2018-12-08
```