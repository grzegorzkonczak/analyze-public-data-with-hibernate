package com.checkrise.countrymgr.controller;

import com.checkrise.countrymgr.model.Country;

import java.math.BigDecimal;
import java.util.Comparator;

// Class for calculating all statistics about country data
public class StatisticsCalculator {
    private CountryHibernateDAO dao;

    public StatisticsCalculator (CountryHibernateDAO dao) {
        this.dao = dao;
    }

    // Calculates minimum adult literacy
    public BigDecimal calculateMinLiteracy() {
        return dao.fetchAllCountries().stream()
                .filter(country -> country.getAdultLiteracyRate() != null)
                .min(Comparator.comparing(Country::getAdultLiteracyRate))
                .get()
                .getAdultLiteracyRate();
    }

    // calculates maximum adult literacy
    public BigDecimal calculateMaxLiteracy() {
        return dao.fetchAllCountries().stream()
                .filter(country -> country.getAdultLiteracyRate() != null)
                .max(Comparator.comparing(Country::getAdultLiteracyRate))
                .get()
                .getAdultLiteracyRate();
    }

    // calculates minimum Internet users indicator
    public BigDecimal calculateMinInternetUsers() {
        return dao.fetchAllCountries().stream()
                .filter(country -> country.getInternetUsers() != null)
                .min(Comparator.comparing(Country::getInternetUsers))
                .get()
                .getInternetUsers();
    }

    // calculates maximum Internet users indicator
    public BigDecimal calculateMaxInternetUsers() {
        return dao.fetchAllCountries().stream()
                .filter(country -> country.getInternetUsers() != null)
                .max(Comparator.comparing(Country::getInternetUsers))
                .get()
                .getInternetUsers();
    }

    // Calculates correlation coefficient
    public BigDecimal calculateCoefficient() {
        // Declaring all variables for calculation given formula from
        // https://www.youtube.com/watch?v=atLZNGsTN6k (around 06:00 min)
        BigDecimal count = BigDecimal.ZERO; // n
        BigDecimal sumIntUsers = BigDecimal.ZERO; // Eps(x)
        BigDecimal sumLiteracy = BigDecimal.ZERO; // Eps(y)
        BigDecimal sumUsersTimesLiteracy = BigDecimal.ZERO; // Eps(xy)
        BigDecimal sumUsersPower = BigDecimal.ZERO; // Eps(x2)
        BigDecimal sumLiteracyPower = BigDecimal.ZERO; // Eps(y2)

        // Calculating all variables
        for (Country country : dao.fetchAllCountries()){
            if (country.getInternetUsers() != null && country.getAdultLiteracyRate() != null){
                count = count.add(BigDecimal.ONE);
                sumIntUsers = sumIntUsers.add(country.getInternetUsers());
                sumLiteracy = sumLiteracy.add(country.getAdultLiteracyRate());
                sumUsersTimesLiteracy = sumUsersTimesLiteracy.add(country.getInternetUsers().multiply(country.getAdultLiteracyRate()));
                sumUsersPower = sumUsersPower.add(country.getInternetUsers().pow(2));
                sumLiteracyPower = sumLiteracyPower.add(country.getAdultLiteracyRate().pow(2));
            }
        }

        // Calculating correlation coefficient

        // Calculate dividend
        BigDecimal correlationDividend = count
                .multiply(sumUsersTimesLiteracy)
                .subtract(sumIntUsers.multiply(sumLiteracy));
        // Calculate left side of divisor
        BigDecimal correlationDivisorLeft = count.multiply(sumUsersPower).subtract(sumIntUsers.pow(2));
        // Calculate right side of divisor
        BigDecimal correlationDivisorRight = count.multiply(sumLiteracyPower).subtract(sumLiteracy.pow(2));
        // Calculate divisor not squared yet
        BigDecimal correlationDivisorNotSquared = correlationDivisorLeft.multiply(correlationDivisorRight);
        // Calculate divisor by casting to double, squaring and casting back to BigDecimal
        BigDecimal correlationDivisor = BigDecimal.valueOf(Math.sqrt(correlationDivisorNotSquared.doubleValue()));
        // Calculate final correlation coefficient
        BigDecimal correlationCoefficient = correlationDividend.divide(correlationDivisor, BigDecimal.ROUND_HALF_UP);
        return correlationCoefficient;
    }
}
