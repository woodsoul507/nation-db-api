package me.givo.nationdbapiproject.dto;

import java.math.BigDecimal;

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
public class CountryStatsDto {

    @EqualsAndHashCode.Include
    private Integer countryId;

    @NotBlank
    @Size(max = 11)
    @EqualsAndHashCode.Include
    private Integer year;

    @Size(max = 11)
    private Long population;

    private BigDecimal gdp;

    public CountryStatsDto(@Size(max = 11) Long population, BigDecimal gdp) {
        this.population = population;
        this.gdp = gdp;
    }

}
