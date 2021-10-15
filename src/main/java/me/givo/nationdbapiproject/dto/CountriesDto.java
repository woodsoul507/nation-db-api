package me.givo.nationdbapiproject.dto;

import java.math.BigDecimal;
import java.sql.Date;

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

    public CountriesDto(@NotBlank @Size(max = 50) String name, @NotBlank BigDecimal area, Date nationalDay,
            @NotBlank @Size(max = 2) String countryCode2, @NotBlank @Size(max = 3) String countryCode3) {
        this.name = name;
        this.area = area;
        this.nationalDay = nationalDay;
        this.countryCode2 = countryCode2;
        this.countryCode3 = countryCode3;
    }

    public CountriesDto(@NotBlank @Size(max = 50) String name, @NotBlank BigDecimal area,
            @NotBlank @Size(max = 2) String countryCode2, @NotBlank @Size(max = 3) String countryCode3) {
        this.name = name;
        this.area = area;
        this.countryCode2 = countryCode2;
        this.countryCode3 = countryCode3;
    }

}
