package me.givo.nationdbapiproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.givo.nationdbapiproject.model.Continents;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegionsDto {
    @EqualsAndHashCode.Include
    private Integer regionId;

    @NotBlank
    @Size(max = 100)
    private String name;

    private Continents continents;

    public RegionsDto(@NotBlank @Size(max = 100) String name, Continents continents) {
        this.name = name;
        this.continents = continents;
    }

}
