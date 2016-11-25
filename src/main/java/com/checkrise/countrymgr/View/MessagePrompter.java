package com.checkrise.countrymgr.View;


import com.checkrise.countrymgr.model.Country;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MessagePrompter {
    private static final Scanner input = new Scanner(System.in);

    private Map<Integer, String> menu;

    // Creates class with populated menu
    public MessagePrompter() {
        menu = new HashMap<>();
        menu.put(1, "Display all countries");
        menu.put(2, "Exit Country Viewer");
    }

    // prompts user to choose action
    public int promptAction() {
        System.out.println("\nYour options:");
        for (Map.Entry<Integer, String> option : menu.entrySet()) {
            System.out.printf("%d.  %s%n", option.getKey(), option.getValue());
        }
        System.out.print("What do you want to do(choose menu option number):   ");
        return input.nextInt();
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Country Data Viewer!");
    }

    public void displayGoodbyeMessage() {
        System.out.println("Thank you for using Country Data Viewer!");
    }

    // Displays default list of all countries
    public void displayCountryList(List<Country> countries) {
        countries.forEach(System.out::println);
    }
}
