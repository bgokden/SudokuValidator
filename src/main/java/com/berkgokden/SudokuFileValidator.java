package com.berkgokden;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by berkgokden on 11/8/14.
 */
public class SudokuFileValidator {
    public SudokuFileValidator(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                if (isValidLine(line)) {
                    System.out.println(validate(line) + ":");
                    System.out.println(line);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String line) {
        Map<Integer, String> boxes = new HashMap<Integer, String>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int offset = (i*27+j*3);
                stringBuilder.setLength(0);
                stringBuilder.append(line.substring(offset,offset+3));
                stringBuilder.append(line.substring(offset+9,offset+12));
                stringBuilder.append(line.substring(offset+18,offset+21));
                String block = stringBuilder.toString();
                if (check(block)==false) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check(String block) {
        int[] checkList = new int[9];
        for (int i = 0; i < block.length(); i++) {
            // there is simple trick to distract a char from another char to get its digits value
            checkList[block.charAt(i) - '1' ] = 1;
        }
        int sum = 0;
        for (int i = 0; i < checkList.length; i++) {
            sum += checkList[i];
        }
        return (sum == 9);
    }

    public static boolean isValidLine(String line) {
        if (line == null || line.length() != 81 || line.charAt(0) == '#' || !line.matches("[1-9]+")) {
            return false;
        }
        return true;
    }
}
