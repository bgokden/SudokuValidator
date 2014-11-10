package com.berkgokden;

import com.berkgokden.Impl.Sudoku9x9BlockValidator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sudoku9x9BlockValidatorTest {
    private static final Logger logger = Logger.getLogger(Sudoku9x9BlockValidatorTest.class);

    @Test
    public void shouldPassWhenCorrectlyValidated() throws Exception {
        Sudoku9x9BlockValidator.underPerformanceTest = true;
        //These are validated manually
        String blockFalse = "147352968238769145569481732371546289426897513895123476984675321653214897712938645";
        boolean mustFalse = Sudoku9x9BlockValidator.validate(blockFalse);
        logger.debug("Must False:" + mustFalse);

        String blockTrue = "147352968238769145569481732371546289426897513895123476984675321653214897712938654";
        boolean mustTrue = Sudoku9x9BlockValidator.validate(blockTrue);
        logger.debug("Must True:" + mustTrue);

        blockFalse = "123456789496738215758192246974215863231687594685349127567824931819563472342971658";
        mustFalse = Sudoku9x9BlockValidator.validate(blockFalse);
        logger.debug("Must False:" + mustFalse);
    }


}