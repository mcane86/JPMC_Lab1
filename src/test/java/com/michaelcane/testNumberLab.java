package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testNumberLab {

    private String example1 = "3:30";
    private String example2 = "(302)731-9k33";
    private String example3 = "3:35p.m.";
    private String example4 = "02:00";
    private String example5 = "18:45";
    private String example6 = "(215) 555-1212";
    private String example7 = "11:45am";
    private String example8 = "12:00p.m.";
    private String example9 = "21:45";


    NumberLab numberLab;
    @Before public void setup() {
        numberLab = new NumberLab();
        numberLab.populateNumberMap();
        numberLab.populateMilitaryMap();
        numberLab.populateTwelveHourMap();
        numberLab.populateDoubleMap();
    }

    @Test
    public void testConvertPhoneNumber() {
        String expected = "Two";
        String actual = numberLab.phoneNumberConverter("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckIfWrong() {
        boolean actual = numberLab.checkIfWrong(example2);
        assertFalse(actual);
    }

    @Test
    public void testPhoneNumberConverter() {
        String expected = " TwoOneFive  FiveFiveFive OneTwoOneTwo";
        String actual = numberLab.phoneNumberConverter(example6);
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckIfMilitary() {
        boolean expected = true;
        boolean actual = numberLab.checkAMOrPM(example3);
        assertEquals(expected, actual);
    }

    @Test
    public void testIsATime() {
        boolean expected = true;
        boolean actual = numberLab.isATime(example1);
        assertEquals(expected, actual);
    }

    @Test
    public void testSecondStringSplitter() {
        String expected = "02";
        String actual = numberLab.stringSplitter(example4)[0];
        assertEquals(expected, actual);
    }

    @Test
    public void testIfOIsNeeded() {
        boolean expected = true;
        String[] newExample = numberLab.stringSplitter(example1);
        boolean actual = numberLab.ifOIsNeeded(newExample);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddTheO1() {
        String expected = "03";
        String actual = numberLab.addTheO(example1)[0];
        assertEquals(expected, actual);
    }

    @Test
    public void testAddTheO2() {
        String expected = "11";
        String actual = numberLab.addTheO(example7)[0];
        assertEquals(expected, actual);
    }

    @Test
    public void testAddTheONegative() {
        String expected = "18";
        String actual = numberLab.addTheO(example5)[0];
        assertEquals(expected, actual);
    }


    @Test
    public void testTimeConverter1() {
        String expected = "Three ThirtyFive Post Meridiem";
        String actual = numberLab.timeConverter(example3);
        assertEquals(expected, actual);
    }

    @Test
    public void testTimeConverter2() {
        String expected = "Eleven FortyFive Ante Meridiem";
        String actual = numberLab.timeConverter(example7);
        assertEquals(expected, actual);
    }

    @Test
    public void testTimeConverter3() {
        String expected = "Twelve O'clock Post Meridiem";
        String actual = numberLab.timeConverter(example8);
        assertEquals(expected, actual);
    }

    @Test
    public void testMilitaryConverter1() {
        String expected = "ZeroTwo Hundred Hours";
        String actual = numberLab.militaryConverter(example4);
        assertEquals(expected, actual);
    }

    @Test
    public void testMilitaryConverter2() {
        String expected = "TwentyOne FortyFive Hundred Hours";
        String actual = numberLab.militaryConverter(example9);
        assertEquals(expected, actual);
    }

    @Test
    public void testMilitaryConverter3() {
        String expected = "Eighteen FortyFive Hundred Hours";
        String actual = numberLab.militaryConverter(example5);
        assertEquals(expected, actual);
    }

}
