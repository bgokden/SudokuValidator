package com.berkgokden;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            System.err.println("File name needed.");
            return;
        }
        String filename = args[0];

        SudokuFileValidator sudokuFileValidator = new SudokuFileValidator();

        List<String> listOfInvalidSolutions = sudokuFileValidator.validate(filename);

        if (listOfInvalidSolutions != null) {
            for (String solution : listOfInvalidSolutions) {
                System.out.println(solution);
            }
            System.out.println("Number of invalid solutions :" + listOfInvalidSolutions.size());
        } else {
            //listOfInvalidSolutions returned null
            System.err.println("File name needed.");
        }
    }
}
