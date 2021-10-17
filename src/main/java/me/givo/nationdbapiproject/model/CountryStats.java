package me.givo.nationdbapiproject.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "country_stats")
public class CountryStats {

    @EmbeddedId
    private CountryStatsId id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("countryId")
    @JoinColumn(name = "country_id")
    private Countries countries;

    @Transient
    private Integer year;

    @Column(name = "population", length = 11, nullable = true)
    private Long population;

    @Column(name = "gdp", nullable = false)
    private BigDecimal gdp;

    public CountryStats() {
    }

    public CountryStats(Countries countries, Integer year, Long population, BigDecimal gdp) {
        this.id = new CountryStatsId(countries.getCountryId(), year);
        this.countries = countries;
        this.year = year;
        this.population = population;
        this.gdp = gdp;
    }

    public CountryStatsId getId() {
        return id;
    }

    public void setId(CountryStatsId id) {
        this.id = id;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public Integer getYear() {
        return id.getYear();
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public BigDecimal getGdp() {
        return gdp;
    }

    public void setGdp(BigDecimal gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "CountryStats [countries=" + countries.getName() + ", gdp=" + gdp + ", country_id=" + id.getCountryId()
                + ", population=" + population + ", year=" + id.getYear() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countries == null) ? 0 : countries.hashCode());
        result = prime * result + ((gdp == null) ? 0 : gdp.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((population == null) ? 0 : population.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CountryStats other = (CountryStats) obj;
        if (countries == null) {
            if (other.countries != null)
                return false;
        } else if (!countries.equals(other.countries))
            return false;
        if (gdp == null) {
            if (other.gdp != null)
                return false;
        } else if (!gdp.equals(other.gdp))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (population == null) {
            if (other.population != null)
                return false;
        } else if (!population.equals(other.population))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

}
