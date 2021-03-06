package me.givo.nationdbapiproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.givo.nationdbapiproject.dto.RegionDto;
import me.givo.nationdbapiproject.service.RegionsServiceImpl;

@RestController
@RequestMapping("/regions")
public class RegionsController {
    private final RegionsServiceImpl regionsService;

    public RegionsController(RegionsServiceImpl regionsService) {
        this.regionsService = regionsService;
    }

    @GetMapping
    public List<RegionDto> findAll() {
        List<RegionDto> response = regionsService.findAll();
        return response;
    }

    @GetMapping("/findBy")
    public RegionDto find(@RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "id", required = false) Integer id) {
        if (name == null && id != null) {
            return regionsService.findById(id);
        }
        if (name != null && id == null) {
            return regionsService.findByName(name);
        }
        return null;
    }

    @PostMapping
    public List<RegionDto> create(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "continent") String continent) {
        regionsService.create(name, continent);
        return regionsService.findAll();
    }

    @DeleteMapping
    public List<RegionDto> delete(@RequestParam("id") Integer id) {
        regionsService.delete(id);
        return regionsService.findAll();
    }

}
