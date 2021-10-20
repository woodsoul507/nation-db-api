package me.givo.nationdbapiproject.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.CountryStatsDto;
import me.givo.nationdbapiproject.service.CountryStatsServiceImpl;

@RestController
@RequestMapping("/countryStats")
public class CountryStatsController {

    private final CountryStatsServiceImpl countryStatsService;

    public CountryStatsController(CountryStatsServiceImpl countryStatsService) {
        this.countryStatsService = countryStatsService;
    }

    @GetMapping
    public List<CountryStatsDto> findAll() {
        List<CountryStatsDto> response = countryStatsService.findAll();
        return response;
    }

    // Returning an Object type since response can be List<CountryStats> or
    // single CountryStats
    @GetMapping("/findBy")
    public Object find(@RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "year", required = false) Integer year) {

        if (country != null && year != null) {
            return countryStatsService.findById(country, year);
        }

        if (country != null && year == null) {
            return countryStatsService.findByCountry(country);
        }

        if (country == null && year != null) {
            return countryStatsService.findByYear(year);
        }

        return null;
    }

    @PostMapping
    public List<CountryStatsDto> create(@RequestParam(name = "country") String country,
            @RequestParam(name = "year") Integer year, @RequestParam(name = "population") Long population,
            @RequestParam(name = "gdp") BigDecimal gdp) {
        countryStatsService.create(country, year, population, gdp);
        return countryStatsService.findAll();
    }

    @DeleteMapping
    public List<CountryStatsDto> delete(@RequestParam(name = "country") String country,
            @RequestParam(name = "year") Integer year) {
        countryStatsService.delete(country, year);
        return countryStatsService.findAll();
    }

}
