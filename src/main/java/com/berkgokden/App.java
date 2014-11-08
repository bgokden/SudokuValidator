package com.berkgokden;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        if (args.length == 0) {
//            System.err.println("File name needed.");
//            return;
//        }
//        String filename = args[0];
        String filename = "file/samples.txt";

        System.out.println( "First development" );

        new SudokuFileValidator(filename);

    }
}
