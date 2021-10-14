package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<ContinentsDto> show() {
        List<ContinentsDto> response = continentsService.getAll();
        System.out.println(response);
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

    @PostMapping("/create/{name}")
    public List<ContinentsDto> create(@PathVariable("name") String name) {
        continentsService.create(new ContinentsDto(name));
        return continentsService.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<ContinentsDto> remove(@PathVariable("id") Integer id) {
        continentsService.delete(id);
        return continentsService.getAll();
    }

}
