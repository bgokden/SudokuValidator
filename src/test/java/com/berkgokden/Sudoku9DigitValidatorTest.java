package com.berkgokden;

import com.berkgokden.Impl.Sudoku9DigitValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sudoku9DigitValidatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        Sudoku9DigitValidator sudoku9DigitValidator = new Sudoku9DigitValidator(null, null);
        assertTrue(sudoku9DigitValidator.validate("123456789"));
        System.out.println("123456789 :"+sudoku9DigitValidator.validate("123456789"));
        assertTrue(sudoku9DigitValidator.validate("123446789"));
        System.out.println("123446789 :"+sudoku9DigitValidator.validate("123446789"));
    }
}