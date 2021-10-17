package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;
import me.givo.nationdbapiproject.service.CountryLanguagesServiceImpl;

@RestController
@RequestMapping("/countryLanguages")
public class CountryLanguagesController {

    private final CountryLanguagesServiceImpl countryLanguagesService;

    public CountryLanguagesController(CountryLanguagesServiceImpl countryLanguagesService) {
        this.countryLanguagesService = countryLanguagesService;
    }

    @GetMapping
    public List<CountryLanguagesDto> findAll() {
        List<CountryLanguagesDto> response = countryLanguagesService.findAll();
        return response;
    }

    // Returning an Object type since response can be List<CountryLanguages> or
    // single CountryLanguages
    @GetMapping("/findBy")
    public Object find(@RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "language", required = false) String language) {

        if (country != null && language != null) {
            return countryLanguagesService.findById(country, language);
        }

        if (country != null && language == null) {
            return countryLanguagesService.findByCountry(country);
        }

        if (country == null && language != null) {
            return countryLanguagesService.findByLanguage(language);
        }

        return null;
    }

    @PostMapping
    public List<CountryLanguagesDto> create(@RequestParam(name = "country") String country,
            @RequestParam(name = "language") String language, @RequestParam(name = "official") Boolean official) {
        countryLanguagesService.create(country, language, official);
        return countryLanguagesService.findAll();
    }

    @DeleteMapping
    public List<CountryLanguagesDto> remove(@RequestParam(name = "country") String country,
            @RequestParam(name = "language") String language) {
        countryLanguagesService.delete(country, language);
        return countryLanguagesService.findAll();
    }

}
