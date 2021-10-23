package me.givo.nationdbapiproject.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedEntityGraph(name = "countries-graph", attributeNodes = { @NamedAttributeNode(value = "name"),
        @NamedAttributeNode("area"), @NamedAttributeNode("nationalDay"), @NamedAttributeNode("countryCode2"),
        @NamedAttributeNode("countryCode3"), @NamedAttributeNode("regions"),
        @NamedAttributeNode(value = "countryLanguages", subgraph = "languages-subgraph"), }, subgraphs = {
                @NamedSubgraph(name = "languages-subgraph", attributeNodes = {
                        @NamedAttributeNode(value = "languages") }) })
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", length = 11, nullable = false)
    private Integer countryId;

    @Column(name = "name", length = 50, nullable = true)
    private String name;

    @Column(name = "area", nullable = false, precision = 2, length = 10)
    private BigDecimal area;

    @Column(name = "national_day", nullable = true)
    private java.sql.Date nationalDay;

    @Column(name = "country_code2", length = 2, nullable = false, unique = true)
    private String countryCode2;

    @Column(name = "country_code3", length = 3, nullable = false, unique = true)
    private String countryCode3;

    @ManyToOne(targetEntity = Region.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    private Region regions;

    @OneToMany(mappedBy = "countries", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CountryLanguage> countryLanguages = new HashSet<CountryLanguage>();

    @OneToMany(mappedBy = "countries", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CountryStats> countryStats = new HashSet<CountryStats>();

    public Country() {
    }

    public Country(String name, BigDecimal area, Date nationalDay, String countryCode2, String countryCode3,
            Region regions) {
        this.name = name;
        this.area = area;
        this.regions = regions;
        this.nationalDay = nationalDay;
        this.countryCode2 = countryCode2;
        this.countryCode3 = countryCode3;
    }

    public Region getRegions() {
        return regions;
    }

    public void setRegions(Region regions) {
        this.regions = regions;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public java.sql.Date getNationalDay() {
        return nationalDay;
    }

    public void setNationalDay(java.sql.Date nationalDay) {
        this.nationalDay = nationalDay;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public Set<CountryLanguage> getCountryLanguages() {
        return countryLanguages;
    }

    public void setCountryLanguages(Set<CountryLanguage> countryLanguages) {
        this.countryLanguages.addAll(countryLanguages);
    }

    @Override
    public String toString() {
        return "Countries [area=" + area + ", countryCode2=" + countryCode2 + ", countryCode3=" + countryCode3
                + ", countryId=" + countryId + ", countryLanguages=" + countryLanguages + ", name=" + name
                + ", nationalDay=" + nationalDay + ", regions=" + regions.getName() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((area == null) ? 0 : area.hashCode());
        result = prime * result + ((countryCode2 == null) ? 0 : countryCode2.hashCode());
        result = prime * result + ((countryCode3 == null) ? 0 : countryCode3.hashCode());
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((countryLanguages == null) ? 0 : countryLanguages.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nationalDay == null) ? 0 : nationalDay.hashCode());
        result = prime * result + ((regions == null) ? 0 : regions.hashCode());
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
        Country other = (Country) obj;
        if (area == null) {
            if (other.area != null)
                return false;
        } else if (!area.equals(other.area))
            return false;
        if (countryCode2 == null) {
            if (other.countryCode2 != null)
                return false;
        } else if (!countryCode2.equals(other.countryCode2))
            return false;
        if (countryCode3 == null) {
            if (other.countryCode3 != null)
                return false;
        } else if (!countryCode3.equals(other.countryCode3))
            return false;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (countryLanguages == null) {
            if (other.countryLanguages != null)
                return false;
        } else if (!countryLanguages.equals(other.countryLanguages))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nationalDay == null) {
            if (other.nationalDay != null)
                return false;
        } else if (!nationalDay.equals(other.nationalDay))
            return false;
        if (regions == null) {
            if (other.regions != null)
                return false;
        } else if (!regions.equals(other.regions))
            return false;
        return true;
    }

}
