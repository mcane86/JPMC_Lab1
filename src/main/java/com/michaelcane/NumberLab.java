package com.michaelcane;

import java.util.HashMap;

public class NumberLab {

    private String stringSplitPattern = "([:])";
    private static HashMap<Integer, String> numberMap = new HashMap<>();
    private static HashMap<Integer, String> militaryMap = new HashMap<>();
    private static HashMap<Character, String> twelveHourMap = new HashMap<>();
    private static HashMap<Integer, String> doubleDigitMap = new HashMap<>();

    public static void populateNumberMap() {
        numberMap.put(1, "One");
        numberMap.put(2, "Two");
        numberMap.put(3, "Three");
        numberMap.put(4, "Four");
        numberMap.put(5, "Five");
        numberMap.put(6, "Six");
        numberMap.put(7, "Seven");
        numberMap.put(8, "Eight");
        numberMap.put(9, "Nine");
        numberMap.put(0, "Zero");
    }

    public static void populateMilitaryMap() {
        militaryMap.put(2, "Twenty");
        militaryMap.put(3, "Thirty");
        militaryMap.put(4, "Forty");
        militaryMap.put(5, "Fifty");
    }

    public static void populateTwelveHourMap() {
        twelveHourMap.put('a', " Ante Meridiem");
        twelveHourMap.put('p', " Post Meridiem");
    }

    public static void populateDoubleMap() {
        doubleDigitMap.put(0, "Ten");
        doubleDigitMap.put(1, "Eleven");
        doubleDigitMap.put(2, "Twelve");
        doubleDigitMap.put(3, "Thirteen");
        doubleDigitMap.put(4, "Fourteen");
        doubleDigitMap.put(5, "Fifteen");
        doubleDigitMap.put(6, "Sixteen");
        doubleDigitMap.put(7, "Seventeen");
        doubleDigitMap.put(8, "Eighteen");
        doubleDigitMap.put(9, "Nineteen");

    }

    public String[] stringSplitter(String input) {
        String[] splitString = input.split(stringSplitPattern);
        return splitString;
    }

    public boolean checkIfWrong(String input) {
        return input.matches("\\d{10}|[1]?(\\s|-)?\\(?\\d{3}\\)?(\\s|-)?\\d{3}(\\s|-)\\d{4}");
    }

    public boolean checkAMOrPM(String input) {
        return input.contains("am") || input.contains("a.m.") || input.contains("pm") || input.contains("p.m.") || input.contains("P.m.");
    }

    public boolean isATime(String input){
        return input.contains(":");
    }

    public boolean ifOIsNeeded(String[] input) {
        if(input[0].length() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String[] addTheO(String input) {
        String[] newInput = stringSplitter(input);
        if(ifOIsNeeded(newInput)) {
            String newElement = "0" + newInput[0];
            newInput[0] = newElement;
            return newInput;
        } else {
            return newInput;
        }
    }

    public static String numberConverter(char input) {
        for(Integer key : numberMap.keySet()) {
            if(key == Character.getNumericValue(input)) {
                return numberMap.get(key);
            }
        }
        return " ";
    }

    public String phoneNumberConverter(String input) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < input.length(); i ++) {
            builder.append(numberConverter( input.charAt(i)) );
        }
        return builder.toString();
    }

    public static String numberConverterMilitary(char input) {
        for(Integer key : militaryMap.keySet()) {
            if(key == Character.getNumericValue(input)) {
                return militaryMap.get(key);
            }
        }
        return "";
    }

    public static String meridemConverter(Character input) {
        for(Character key : twelveHourMap.keySet()) {
            if(key.equals(input)) {
                return twelveHourMap.get(key);
            }
        }
        return "";
    }

    public static String doubleDigitConverter(char input) {
        for(Integer key : doubleDigitMap.keySet()) {
            if(key == Character.getNumericValue(input)) {
                return doubleDigitMap.get(key);
            }
        }
        return "";
    }

    public String timeConverter(String input) {
        StringBuilder builder= new StringBuilder();
        String[] newInput = stringSplitter(input);

        if(newInput[0].length() == 1) {
            builder.append(numberConverter( newInput[0].charAt(0)));
        } else {
            builder.append(doubleDigitConverter( newInput[0].charAt(1)));
        }

        builder.append(" ");

        if(newInput[1].charAt(0) == '0' && newInput[1].charAt(1) == '0') {
            builder.append("O'clock");
        } else {
            builder.append(numberConverterMilitary(newInput[1].charAt(0)));
            builder.append(numberConverter(newInput[1].charAt(1)));
        }
        builder.append(meridemConverter( newInput[1].charAt(2)));

        return builder.toString();
    }

    public String militaryConverter(String input) {
        StringBuilder builder = new StringBuilder();
        String[] newInput = addTheO(input);

        if(newInput[0].charAt(0) > '2') {
            return "Sorry, bad input";

        }

        if(newInput[0].charAt(0) == '0') {
            builder.append("Zero");
            builder.append(numberConverter( newInput[0].charAt(1)));
        } else if (newInput[0].charAt(0) == '1') {
            builder.append(doubleDigitConverter( newInput[0].charAt(1)));
        } else {
            builder.append(numberConverterMilitary( newInput[0].charAt(0)));
            builder.append(numberConverter( newInput[0].charAt(1)));
        }

        builder.append(" ");

        if(newInput[1].charAt(0) == '0' && newInput[1].charAt(1) == '0') {
            builder.append("");
        } else {
            builder.append(numberConverterMilitary( newInput[1].charAt(0)));
            builder.append(numberConverter( newInput[1].charAt(1)));
            builder.append(" ");
        }

        builder.append("Hundred Hours");

        return builder.toString();
    }

    public void giveMeNumber(String choice){
        populateNumberMap();
        populateMilitaryMap();
        populateTwelveHourMap();
        populateDoubleMap();
        if(isATime(choice)) {
            if(checkAMOrPM(choice)) {
                System.out.println(timeConverter(choice));
            } else {
                System.out.println(militaryConverter(choice));
            }
        } else {
            if(checkIfWrong(choice)) {
                System.out.println(phoneNumberConverter(choice));
            } else {
                System.out.println("Sorry, wrong input");
            }
        }
    }
}
