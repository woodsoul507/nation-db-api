package me.givo.nationdbapiproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CountriesController {

    @GetMapping(value = "/countries")
    public Object getCountries(@RequestParam(required = false, name = "findByName") String name) {

        return new Object();
    }

}
