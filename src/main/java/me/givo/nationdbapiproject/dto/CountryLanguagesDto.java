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
public class CountryLanguagesDto {

    @EqualsAndHashCode.Include
    private Integer countryId;

    @EqualsAndHashCode.Include
    private Integer languageId;

    @NotBlank
    @Size(max = 1)
    private Integer official;

    public CountryLanguagesDto(@NotBlank @Size(max = 1) Integer official) {
        this.official = official;
    }

}
