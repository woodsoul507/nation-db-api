package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.RegionsDto;
import me.givo.nationdbapiproject.service.RegionsServiceImpl;

@RestController
@RequestMapping("/regions")
public class RegionsController {
    private final RegionsServiceImpl regionsService;

    public RegionsController(RegionsServiceImpl regionsService) {
        this.regionsService = regionsService;
    }

    @GetMapping
    public List<RegionsDto> show() {
        List<RegionsDto> response = regionsService.getAll();
        System.out.println(response);
        return response;
    }

    @GetMapping("/findBy")
    public RegionsDto find(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) Integer id) {
        if (name == null && id != null) {
            return regionsService.findById(id);
        }
        if (name != null && id == null) {
            return regionsService.findByName(name);
        }
        return null;
    }

    @PostMapping("/create")
    public List<RegionsDto> create(@RequestParam(name = "name") String name,
            @RequestParam(name = "continentId") Integer continentId) {
        // regionsService.create(new RegionsDto(name, continentId));
        return regionsService.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<RegionsDto> remove(@PathVariable("id") Integer id) {
        regionsService.delete(id);
        return regionsService.getAll();
    }

}
