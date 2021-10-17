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
    private CountryStatsIdDto id;

    @NotBlank
    @Size(max = 11)
    private Long population;

    @NotBlank
    private BigDecimal gdp;
}
