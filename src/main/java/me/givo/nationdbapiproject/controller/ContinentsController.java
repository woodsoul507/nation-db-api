package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.ContinentsDto;
import me.givo.nationdbapiproject.service.ContinentsServiceImpl;

@RestController
@RequestMapping("/continents")
public class ContinentsController {

    private final ContinentsServiceImpl continentsService;

    public ContinentsController(ContinentsServiceImpl continentsService) {
        this.continentsService = continentsService;
    }

    @GetMapping
    public List<ContinentsDto> findAll() {
        List<ContinentsDto> response = continentsService.findAll();
        return response;
    }

    @GetMapping("/findBy")
    public ContinentsDto find(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) Integer id) {
        if (name == null && id != null) {
            return continentsService.findById(id);
        }
        if (name != null && id == null) {
            return continentsService.findByName(name);
        }
        return null;
    }

    @PostMapping
    public List<ContinentsDto> create(@RequestParam("name") String name) {
        continentsService.create(new ContinentsDto(name));
        return continentsService.findAll();
    }

    @DeleteMapping
    public List<ContinentsDto> delete(@RequestParam("id") Integer id) {
        continentsService.delete(id);
        return continentsService.findAll();
    }

}
