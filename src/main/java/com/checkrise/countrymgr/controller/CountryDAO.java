package com.checkrise.countrymgr.controller;


import com.checkrise.countrymgr.model.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> fetchAllCountries();
}
