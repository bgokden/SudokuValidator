package com.berkgokden;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuFileValidatorTest {

    private SudokuFileValidator sudokuFileValidator;

    @Before
    public void setUp() throws Exception {
        String filename = "file/samples.txt";

        System.out.println( "First development" );

        this.sudokuFileValidator = new SudokuFileValidator(filename);
    }

    @Test
    public void testEmptyCollection() {
        assertTrue(true);
        System.out.println("@Test - true");
    }

    @Test
    public void shouldReturnFalse() {
        String line = "123453789578139624496872153952381467641297835387564291719623548864915372235748916";
        System.out.println(sudokuFileValidator.validate(line) + ":");
        System.out.println(line);
        assertTrue(sudokuFileValidator.validate(line));
        System.out.println("@Test - true");
    }

    @After
    public void tearDown() throws Exception {

    }
}