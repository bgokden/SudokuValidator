package com.berkgokden.Impl;

import com.berkgokden.ISudokuValidator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by berkgokden on 11/8/14.
 */
public class SudokuFileValidator implements ISudokuValidator {

    static final Logger logger = Logger.getLogger(SudokuFileValidator.class);

    public SudokuFileValidator() {
    }

    public List<String> validate(String filename) {
        Reader reader = null;
        try {
            File file = new File(filename);
            reader = new FileReader(file);
        } catch (Exception e) {
            logger.warn("File can not be opened :" + filename, e);
            System.err.println("File can not be opened :" + filename);
            //e.printStackTrace();
            return null;
        }
        return validate(reader);
    }

    public List<String> validate(Reader reader) {
        if (reader == null) {
            return null;
        }
        List<String> list = new LinkedList<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, Future<Boolean>> lineValidations = new ConcurrentHashMap<String, Future<Boolean>>();
        int numberofvalidlines = 0;
        try {
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                if (isValidLine(line)) {
                    Future<Boolean> future = executorService.submit(new Sudoku9x9BlockValidator(executorService, line));
                    lineValidations.put(line, future);
                    numberofvalidlines++;
                }
            }
            br.close();

            //int numberOfInvalidSolutions = 0;
            int numberofprocessedlines = 0;
            for (Map.Entry<String, Future<Boolean>> entry : lineValidations.entrySet())
            {

                try {
                    Boolean result = entry.getValue().get();
                    numberofprocessedlines++;
                    if ( result == false) {
                        list.add(entry.getKey());
                        //System.out.println(entry.getKey());
                        //numberOfInvalidSolutions++;
                    }
                } catch (InterruptedException e) {
                    logger.warn("A thread interrupted unexpectedly while working on string:"+entry.getKey(), e);
                    System.err.println("A thread interrupted unexpectedly while working on string:"+entry.getKey());
                    //e.printStackTrace();
                } catch (ExecutionException e) {
                    logger.warn("A thread experienced error while working on string:"+entry.getKey(), e);
                    System.err.println("A thread experienced error while working on string:"+entry.getKey());
                    //e.printStackTrace();
                }
            }
            //System.out.println("Number Of Invalid Solutions:"+numberOfInvalidSolutions);
            //debug point System.out.println(numberofvalidlines+"/"+numberofprocessedlines);
            logger.debug("("+numberofvalidlines+"=="+numberofprocessedlines+") = "+(numberofvalidlines==numberofprocessedlines));
        } catch (IOException e) {
            logger.warn("Can not read stream.", e);
            System.err.println("Can not read stream.");
            list = null; //return null when there is an error effecting all source
            // e.printStackTrace();
        }

        executorService.shutdown();
        return list;
    }

    public static boolean isValidLine(String line) {
        //if a line is not valid for validation it will be skipped
        //Even if the line is not defined as a comment, it will be skipped (it is a feature not a bug)
        if (line == null || line.length() != 81 || line.charAt(0) == '#' || !line.matches("[1-9]+")) {
            return false;
        }
        return true;
    }
}