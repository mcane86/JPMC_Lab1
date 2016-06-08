package com.michaelcane;
import static com.michaelcane.UserInputHandler.*;

public class Application {

    public static void main(String[] args) {
        NumberLab numberLab = new NumberLab();
        String choice = promptUserForString("Please give me a Telephone Number or a Time (12-hour or Military)");
        numberLab.giveMeNumber(choice);

    }
}
