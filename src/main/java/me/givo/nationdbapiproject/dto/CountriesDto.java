package me.givo.nationdbapiproject.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.Regions;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CountriesDto {

    @EqualsAndHashCode.Include
    private Integer countryId;

    private String name;

    private BigDecimal area;

    private java.sql.Date nationalDay;

    private String countryCode2;

    private String countryCode3;

    private Regions regions;

    private Set<CountryLanguages> countryLanguages = new HashSet<CountryLanguages>();

}
