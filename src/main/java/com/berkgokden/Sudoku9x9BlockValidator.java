package com.berkgokden;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by berkgokden on 11/9/14.
 */
public class Sudoku9x9BlockValidator implements Callable<Boolean> {
    private String sudoku9x9Block;
    private ExecutorService executorService;
    private volatile boolean check;
    public Sudoku9x9BlockValidator(ExecutorService executorService, String sudoku9x9Block) {
        this.executorService = executorService;
        this.sudoku9x9Block = sudoku9x9Block;
        this.check = true;
    }

    @Override
    public Boolean call() throws Exception {
        Map<Integer, Future<Boolean>> cases = new ConcurrentHashMap<Integer, Future<Boolean>>();
        StringBuilder stringBuilder = new StringBuilder();

        int caseId = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isCheck()) {
                    return false;
                }
                int offset = (i*27+j*3);
                stringBuilder.setLength(0);
                stringBuilder.append(sudoku9x9Block.substring(offset,offset+3));
                stringBuilder.append(sudoku9x9Block.substring(offset+9,offset+12));
                stringBuilder.append(sudoku9x9Block.substring(offset+18,offset+21));
                Future<Boolean> future = executorService.submit(new Sudoku9DigitValidator(stringBuilder.toString(), this));
                cases.put(caseId, future);
                caseId++;
            }
        }

        //rows
        for (int i = 0; i < 9; i++) {
            if (!isCheck()) {
                return false;
            }
            Future<Boolean> future = executorService.submit(new Sudoku9DigitValidator(sudoku9x9Block.substring(i*9,i*9+9), this));
            cases.put(caseId, future);
            caseId++;
        }

        //columns
        for (int i = 0; i < 9; i++) {
            if (!isCheck()) {
                return false;
            }
            stringBuilder.setLength(0);
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(sudoku9x9Block.charAt(i+j*9));
            }

            Future<Boolean> future = executorService.submit(new Sudoku9DigitValidator(stringBuilder.toString(), this));
            cases.put(caseId, future);
            caseId++;
        }

        //main reason for this loop to ensure that every check is done and completed.
        for (Map.Entry<Integer, Future<Boolean>> aCase : cases.entrySet()) {
            if (aCase.getValue().equals(false) || !isCheck()) {
                return false;
            }
        }

        return true;
    }

    public synchronized boolean isCheck() {
        return check;
    }

    public synchronized void setCheck(boolean check) {
        this.check = check;
    }

    public synchronized void setCheckFalse() {
        this.check = false;
    }
}
