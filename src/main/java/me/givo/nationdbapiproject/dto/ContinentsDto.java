package me.givo.nationdbapiproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContinentsDto {
    @EqualsAndHashCode.Include
    private Integer continentId;

    @NotBlank
    @Size(max = 255)
    private String name;

    public ContinentsDto(String name) {
        this.name = name;
    }

}
