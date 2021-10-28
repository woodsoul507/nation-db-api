package me.givo.nationdbapiproject.controller;

import me.givo.nationdbapiproject.dto.ContinentDto;
import me.givo.nationdbapiproject.dto.CustomResponseDto;
import me.givo.nationdbapiproject.service.ContinentsServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/continents")
public class ContinentsController {

    private final ContinentsServiceImpl continentsService;

    public ContinentsController(ContinentsServiceImpl continentsService) {
        this.continentsService = continentsService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<ContinentDto> continentDtoList = continentsService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(continentDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/findById")
    @Cacheable(value = "findContinentById")
    public ResponseEntity<Object> findById(@RequestParam(name = "id", required = false) @NotNull
                                           @Min(1) Integer id) {
        List<ContinentDto> continentDtoList;
        try {
            continentDtoList = continentsService.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
        try {
            ContinentDto continentDtoById = continentDtoList.stream()
                    .filter(continentDto -> id.equals(continentDto.getContinentId())).findAny().orElse(null);
            if (continentDtoById != null) {
                return ResponseEntity.status(HttpStatus.OK).body(continentDtoById);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponseDto("404",
                        "findById.id: not Continent with id=" + id,
                        "/continents/findById"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @GetMapping("/findByName")
    @Cacheable(value = "findContinentByName")
    public ResponseEntity<Object> findByName(@RequestParam(name = "name", required = false) @NotNull
                                             @NotEmpty @NotBlank
                                             @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Only letters and spaces allowed.")
                                             @Size(max = 255) String name) {
        List<ContinentDto> continentDtoList;
        try {
            continentDtoList = continentsService.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

        try {
            ContinentDto continentDtoByName = continentDtoList.stream()
                    .filter(continentDto -> name.equals(continentDto.getName())).findAny().orElse(null);
            if (continentDtoByName != null) {
                return ResponseEntity.status(HttpStatus.OK).body(continentDtoByName);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponseDto("404",
                        "findById.id: not Continent with name=" + name,
                        "/continents/findByName"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestParam(value = "name") @NotNull
                                         @NotEmpty @NotBlank
                                         @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Only letters and spaces allowed.")
                                         @Size(max = 255) String name) {
        try {
            ContinentDto createdContinentDto = continentsService.create(name);
            continentsService.findAll();
            return ResponseEntity.status(HttpStatus.CREATED).body(createdContinentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") @NotNull
                                         @Min(1) Integer id) {
        try {
            continentsService.delete(id);
            continentsService.findAll();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
