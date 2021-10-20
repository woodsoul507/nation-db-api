package me.givo.nationdbapiproject.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.CountriesDto;
import me.givo.nationdbapiproject.service.CountriesServiceImpl;

@RestController
@RequestMapping("/countries")
public class CountriesController {
    private final CountriesServiceImpl countriesService;

    public CountriesController(CountriesServiceImpl countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping
    public List<CountriesDto> findAll() {
        List<CountriesDto> response = countriesService.findAll();
        return response;
    }

    @GetMapping("/findBy")
    public CountriesDto find(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) Integer id) {
        if (name == null && id != null) {
            return countriesService.findById(id);
        }
        if (name != null && id == null) {
            return countriesService.findByName(name);
        }
        return null;
    }

    @PostMapping
    public List<CountriesDto> create(@RequestParam(name = "name") String name,
            @RequestParam(name = "area") BigDecimal area, @RequestParam(name = "date", required = false) Long date,
            @RequestParam(name = "code2") String code2, @RequestParam(name = "code3") String code3,
            @RequestParam(name = "region") String region) {
        countriesService.create(name, area, date, code2, code3, region);
        return countriesService.findAll();
    }

    @DeleteMapping
    public List<CountriesDto> delete(@RequestParam("id") Integer id) {
        countriesService.delete(id);
        return countriesService.findAll();
    }

}
