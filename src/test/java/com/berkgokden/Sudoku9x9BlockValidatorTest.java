package com.berkgokden;

import com.berkgokden.Impl.Sudoku9x9BlockValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sudoku9x9BlockValidatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        String blockFalse = "147352968238769145569481732371546289426897513895123476984675321653214897712938645";
        String blockTrue = "147352968238769145569481732371546289426897513895123476984675321653214897712938654";
        //Sudoku9x9BlockValidator sudoku9x9BlockValidator = new Sudoku9x9BlockValidator( null);

        boolean mustFalse = Sudoku9x9BlockValidator.validate(blockFalse);
        System.out.println("Must False:" + mustFalse);

        boolean mustTrue = Sudoku9x9BlockValidator.validate(blockTrue);
        System.out.println("Must True:" + mustTrue);

        blockFalse = "123456789496738215758192246974215863231687594685349127567824931819563472342971658";

        mustFalse = Sudoku9x9BlockValidator.validate(blockFalse);
        System.out.println("Must False:" + mustFalse);

        //load test
        for (int i = 0; i < 10000; i++) {
            mustFalse = Sudoku9x9BlockValidator.validate(blockFalse);
        }
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