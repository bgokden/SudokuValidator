package com.berkgokden;

import com.berkgokden.Impl.SudokuFileValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
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

        File file = new File(filename);
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("File couldn't be opened.");
            return;
            //e.printStackTrace();
        }

        ISudokuValidator sudokuValidator = new SudokuFileValidator();

        List<String> listOfInvalidSolutions = sudokuValidator.validate(reader);

        if (listOfInvalidSolutions != null) {
            System.out.println("Invalid Solutions :" );
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
