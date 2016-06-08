package com.michaelcane;

import java.util.Scanner;

public class UserInputHandler {

    public static Scanner input = new Scanner(System.in);

    public static String promptUserForString(String msg) {
        promptUser(msg);
        return input.nextLine();
    }

    public static void promptUser(String msg) {
        System.out.println(msg);
    }
}
