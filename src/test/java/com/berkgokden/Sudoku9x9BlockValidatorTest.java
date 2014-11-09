package com.berkgokden;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class Sudoku9x9BlockValidatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String blockFalse = "147352968238769145569481732371546289426897513895123476984675321653214897712938645";
        String blockTrue = "147352968238769145569481732371546289426897513895123476984675321653214897712938654";
        Sudoku9x9BlockValidator sudoku9x9BlockValidator = new Sudoku9x9BlockValidator(executorService, null);

        boolean mustFalse = sudoku9x9BlockValidator.validate(blockFalse);
        System.out.println("Must False:" + mustFalse);

        boolean mustTrue = sudoku9x9BlockValidator.validate(blockTrue);
        System.out.println("Must True:" + mustTrue);

        String testEfalse = "123456789496738215758192246974215863231687594685349127567824931819563472342971658";

        boolean efalse = sudoku9x9BlockValidator.validate(testEfalse);
        System.out.println("Expected False:" + efalse);

        executorService.shutdown();

    }

    @Test
    public void testIsCheck() throws Exception {

    }

    @Test
    public void testSetCheck() throws Exception {

    }

    @Test
    public void testSetCheckFalse() throws Exception {

    }
}