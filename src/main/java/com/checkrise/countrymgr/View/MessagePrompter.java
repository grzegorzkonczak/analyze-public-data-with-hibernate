package com.checkrise.countrymgr.View;


import com.checkrise.countrymgr.model.Country;
import com.checkrise.countrymgr.model.Country.CountryBuilder;

import java.math.BigDecimal;
import java.util.*;

public class MessagePrompter {
    private static final Scanner input = new Scanner(System.in);

    private Map<Integer, String> menu;

    // Creates class with populated menu
    public MessagePrompter() {
        menu = new HashMap<>();
        menu.put(1, "Display all countries");
        menu.put(2, "Display Statistics");
        menu.put(3, "Edit Country Data");
        menu.put(4, "Add new Country");
        menu.put(5, "Exit Country Viewer");
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

    // Prompts user for information about country he wants to update
    public Country promptEditAction(List<Country> countries) {
        System.out.printf("%nYou will update country data now%n");
        Country country = new Country();

        // Display all country codes for user to choose
        int count = 0;
        for (Country c : countries){
            System.out.print(c.getCode() + " | ");
            count++;
            if (count % 10 == 0){
                System.out.println();
            }
        }
        System.out.println("\nPlease enter country code from above list: ");

        // Prompts user for data to update
        input.nextLine();
        country.setCode(input.nextLine());
        System.out.println("Please enter country name: ");
        country.setName(input.nextLine());
        try {
            System.out.println("Please enter country Internet User indicator: ");
            Double dv = Double.parseDouble(input.nextLine());
            country.setInternetUsers(BigDecimal.valueOf(dv));
            System.out.println("Please enter country Adult Literacy Rate indicator: ");
            dv = Double.parseDouble(input.nextLine());
            country.setAdultLiteracyRate(BigDecimal.valueOf(dv));
        // If wrong input return null and end method
        } catch (InputMismatchException | NumberFormatException ex){
            System.out.println("Wrong input format, try again...");
            return null;
        }

        return country;
    }

    // Prompts user for information about country he wants to add to database
    public Country promptForNewCountry() {
        System.out.printf("%nYou will add country to database now%n");
        input.nextLine();

        // Code and name are mandatory
        System.out.println("\nPlease enter country code : ");
        String code = input.nextLine();
        System.out.println("Please enter country name: ");
        String name = input.nextLine();

        CountryBuilder builder = new CountryBuilder(code, name);

        // Optional fields for creation

        // Optional internet users
        System.out.println("If you want to add internet users indicator enter 'Y' and press enter...");
        if (input.nextLine().equalsIgnoreCase("Y")){
            System.out.println("Please enter country Internet User indicator: ");
            Double dv = Double.parseDouble(input.nextLine());
            builder.withInternetUsers(BigDecimal.valueOf(dv));
        }
        // Optional adult literacy
        System.out.println("If you want to add adult literacy indicator enter 'Y' and press enter...");
        if (input.nextLine().equalsIgnoreCase("Y")){
            System.out.println("Please enter country Adult Literacy indicator: ");
            Double dv = Double.parseDouble(input.nextLine());
            builder.withAdultLiteracyRate(BigDecimal.valueOf(dv));
        }

        return builder.build();
    }
}
