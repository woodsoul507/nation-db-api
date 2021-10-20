package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.LanguagesDto;
import me.givo.nationdbapiproject.service.LanguagesServiceImpl;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private final LanguagesServiceImpl languagesService;

    public LanguagesController(LanguagesServiceImpl languagesService) {
        this.languagesService = languagesService;
    }

    @GetMapping
    public List<LanguagesDto> findAll() {
        List<LanguagesDto> response = languagesService.findAll();
        return response;
    }

    @GetMapping("/findBy")
    public LanguagesDto find(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) Integer id) {
        if (name == null && id != null) {
            return languagesService.findById(id);
        }
        if (name != null && id == null) {
            return languagesService.findByName(name);
        }
        return null;
    }

    @PostMapping
    public List<LanguagesDto> create(@RequestParam("name") String name) {
        languagesService.create(new LanguagesDto(name));
        return languagesService.findAll();
    }

    @DeleteMapping
    public List<LanguagesDto> delete(@RequestParam("id") Integer id) {
        languagesService.delete(id);
        return languagesService.findAll();
    }

}
