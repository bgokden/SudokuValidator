package com.berkgokden;

import com.berkgokden.Impl.Sudoku9DigitValidator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sudoku9DigitValidatorTest {

    private static final Logger logger = Logger.getLogger(Sudoku9DigitValidatorTest.class);

    @Test
    public void shouldPassIfAllStringsAreValid() throws Exception {
        String validStringCombination = "123456789";
        boolean result = false;
        for (int i = 0; i < 100; i++) {
            validStringCombination = shuffle(validStringCombination);
            result = Sudoku9DigitValidator.validate(validStringCombination);
            //logger.debug(validStringCombination+":"+result);
            assertTrue(result);
        }
        logger.debug("Validation of valid strings are successful");
    }

    @Test
    public void shouldPassIfAllStringsAreInvalid() throws Exception {
        String invalidStringCombination = "113456789";
        boolean result = false;
        for (int i = 0; i < 10; i++) {
            invalidStringCombination = shuffle(invalidStringCombination);
            result = Sudoku9DigitValidator.validate(invalidStringCombination);
            //logger.debug(invalidStringCombination+":"+result);
            assertFalse(result);
        }
        invalidStringCombination = "122456789";
        for (int i = 0; i < 10; i++) {
            invalidStringCombination = shuffle(invalidStringCombination);
            result = Sudoku9DigitValidator.validate(invalidStringCombination);
            //logger.debug(invalidStringCombination+":"+result);
            assertFalse(result);
        }
        invalidStringCombination = "123356789";
        for (int i = 0; i < 10; i++) {
            invalidStringCombination = shuffle(invalidStringCombination);
            result = Sudoku9DigitValidator.validate(invalidStringCombination);
            //logger.debug(invalidStringCombination+":"+result);
            assertFalse(result);
        }
        invalidStringCombination = "123446789";
        for (int i = 0; i < 10; i++) {
            invalidStringCombination = shuffle(invalidStringCombination);
            result = Sudoku9DigitValidator.validate(invalidStringCombination);
            //logger.debug(invalidStringCombination+":"+result);
            assertFalse(result);
        }
        logger.debug("Validation of invalid strings are successful");
    }

    //source: http://stackoverflow.com/questions/3316674/how-to-shuffle-characters-in-a-string
    public static String shuffle(String s)
    {
        String shuffledString = "";
        while (s.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * s.length());
            char c = s.charAt(index);
            s = s.substring(0,index)+s.substring(index+1);
            shuffledString += c;
        }
        return shuffledString;

    }
}