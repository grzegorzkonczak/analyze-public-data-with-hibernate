package com.checkrise.countrymgr.controller;


import com.checkrise.countrymgr.model.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> fetchAllCountries();

    void update(Country country);

    Country findByCode(String code);

    void save(Country country);
}
