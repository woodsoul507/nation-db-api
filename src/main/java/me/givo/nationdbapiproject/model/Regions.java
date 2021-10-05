package me.givo.nationdbapiproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
public class Regions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id", length = 11, nullable = false)
    private Integer regionId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(targetEntity = Continents.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", referencedColumnName = "continent_id")
    private Continents continents;

    @OneToMany(targetEntity = Countries.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    private List<Countries> countries = new ArrayList<>();

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continents getContinents() {
        return continents;
    }

    public void setContinents(Continents continents) {
        this.continents = continents;
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Regions [continents=" + continents + ", countries=" + countries + ", name=" + name + ", region_id="
                + regionId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((continents == null) ? 0 : continents.hashCode());
        result = prime * result + ((countries == null) ? 0 : countries.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
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
        Regions other = (Regions) obj;
        if (continents == null) {
            if (other.continents != null)
                return false;
        } else if (!continents.equals(other.continents))
            return false;
        if (countries == null) {
            if (other.countries != null)
                return false;
        } else if (!countries.equals(other.countries))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (regionId == null) {
            if (other.regionId != null)
                return false;
        } else if (!regionId.equals(other.regionId))
            return false;
        return true;
    }

}
