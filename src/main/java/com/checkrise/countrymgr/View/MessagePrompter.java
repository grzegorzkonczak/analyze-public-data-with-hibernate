package com.checkrise.countrymgr.View;


import com.checkrise.countrymgr.model.Country;

import java.math.BigDecimal;
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
        menu.put(2, "Display Statistics");
        menu.put(3, "Exit Country Viewer");
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

    // Displays welcome message to user
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Country Data Viewer!");
    }

    // Displays goodbye message to user
    public void displayGoodbyeMessage() {
        System.out.println("Thank you for using Country Data Viewer!");
    }

    // Displays formatted list of all countries
    public void displayCountryList(List<Country> countries) {
        System.out.printf("%-30s%20s%20s%n", "Country", "Internet Users", "Literacy");
        System.out.printf("-----------------------------------------------------------------------%n");
        for(Country country : countries){
            // Displays countries with full data
            if (country.getInternetUsers() != null && country.getAdultLiteracyRate() != null) {
                System.out.printf("%-30s%20.2f%20.2f%n", country.getName(),
                        country.getInternetUsers(), country.getAdultLiteracyRate());
            //  Displays countries with missing both numeric pieces of data
            } else if (country.getInternetUsers() == null && country.getAdultLiteracyRate() == null){
                System.out.printf("%-30s%20s%20s%n", country.getName(),
                        "--", "--");
            //  Displays countries when no data about internet users present
            } else if (country.getInternetUsers() == null) {
                System.out.printf("%-30s%20s%20.2f%n", country.getName(),
                        "--", country.getAdultLiteracyRate());
            //  Displays countries when no data about adult literacy present
            } else if (country.getAdultLiteracyRate() == null) {
                System.out.printf("%-30s%20.2f%20s%n", country.getName(),
                        country.getInternetUsers(), "--");
            }
        }
    }

    // Displays statistics to user
    public void displayStatistics(Map<String, BigDecimal> statistics) {
        System.out.printf("%nMinimum Adult Literacy: %.2f%n", statistics.get("minAdultLiteracy"));
        System.out.printf("%nMaximum Adult Literacy: %.2f%n", statistics.get("maxAdultLiteracy"));
        System.out.printf("%nMinimum Internet Users: %.2f%n", statistics.get("minInternetUsers"));
        System.out.printf("%nMaximum Internet Users: %.2f%n", statistics.get("maxInternetUsers"));
        System.out.printf("%nCorrelation Coefficient: %.2f%n", statistics.get("coefficient"));
    }
}
