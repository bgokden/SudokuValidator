# Sudoku Validator

### Installation
```
$ git clone https://github.com/bgokden/SudokuValidator.git
```

### Usage
```
$ cd SudokuValidator
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="com.berkgokden.App" -Dexec.args="/filepath/filename.txt" 
```
### Usage as Jar
If you want to run as a package
```
$ mvn clean package
$ java -jar target/SudokuValidator-1.0-SNAPSHOT-jar-with-dependencies.jar /filepath/filename.txt
```

Have Fun :)
