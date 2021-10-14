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
public class CountriesDto {

    @EqualsAndHashCode.Include
    private Integer countryId;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private BigDecimal area;

    private java.sql.Date nationalDay;

    @NotBlank
    @Size(max = 2)
    private String countryCode2;

    @NotBlank
    @Size(max = 3)
    private String countryCode3;

    private Integer regionId;

}
