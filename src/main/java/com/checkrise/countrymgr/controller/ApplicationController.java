package com.checkrise.countrymgr.controller;

import com.checkrise.countrymgr.View.MessagePrompter;
import com.checkrise.countrymgr.model.Country;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ApplicationController {
    private MessagePrompter prompter;
    private CountryHibernateDAO dao;

    public ApplicationController() {
        prompter = new MessagePrompter();
        dao = new CountryHibernateDAO();
    }

    // Main loop of program, executes option chosen by user
    public void run() {
        int choice;
        prompter.displayWelcomeMessage();
        do {
            choice = prompter.promptAction();
            switch (choice){
                case 1:
                    // Display a list of countries
                    prompter.displayCountryList(dao.fetchAllCountries());
                    break;
                case 2:
                    // Calculate and display statistics about countries
                    Map<String, BigDecimal> statistics = calculateStatistics();
                    prompter.displayStatistics(statistics);
                    break;
                case 3:
                    // Edit selected country data

                    // Prompt user about edit action for country information
                    Country countryUpdated = prompter.promptEditAction(dao.fetchAllCountries());
                    if (countryUpdated != null || dao.findByCode(countryUpdated.getCode()) != null) {
                        dao.update(countryUpdated);
                    }
                    break;
                case 4:
                    // Displays goodbye message to user
                    prompter.displayGoodbyeMessage();
                    break;
            }
        } while (choice != 4);
        System.exit(0);
    }

    // Calculates minimum and maximum of each indicator.
    // Calculates correlation coefficient.
    // Returns results in map
    private Map<String,BigDecimal> calculateStatistics() {
        Map<String, BigDecimal> statistics = new HashMap<>();
        StatisticsCalculator calculator = new StatisticsCalculator(dao);
        statistics.put("minAdultLiteracy", calculator.calculateMinLiteracy());
        statistics.put("maxAdultLiteracy", calculator.calculateMaxLiteracy());
        statistics.put("minInternetUsers", calculator.calculateMinInternetUsers());
        statistics.put("maxInternetUsers", calculator.calculateMaxInternetUsers());
        statistics.put("coefficient", calculator.calculateCoefficient());
        return statistics;
    }
}
