package com.checkrise.countrymgr.controller;

import com.checkrise.countrymgr.View.MessagePrompter;

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
                    // Displays goodbye message to user
                    prompter.displayGoodbyeMessage();
                    break;
            }
        } while (choice != 2);
        System.exit(0);
    }
}
