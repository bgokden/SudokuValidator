package com.berkgokden;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by berkgokden on 11/8/14.
 */
public class SudokuFileValidator {
    private String file;

    public SudokuFileValidator() {


    }

    public List<String> validate(String filename) {
        try {
            File file = new File(filename);
            return validate(file);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<String> validate(File file) {
        if (file == null) {
            return null;
        }
        List<String> list = new LinkedList<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, Future<Boolean>> lineValidations = new ConcurrentHashMap<String, Future<Boolean>>();
        int numberofvalidlines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
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
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Number Of Invalid Solutions:"+numberOfInvalidSolutions);
            System.out.println(numberofvalidlines+"/"+numberofprocessedlines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return list;
    }

    public static boolean isValidLine(String line) {
        if (line == null || line.length() != 81 || line.charAt(0) == '#' || !line.matches("[1-9]+")) {
            return false;
        }
        return true;
    }
}
