package me.givo.nationdbapiproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public Regions() {
    }

    public Regions(String name, Continents continents) {
        this.name = name;
        this.continents = continents;
    }

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

    @Override
    public String toString() {
        return "Regions [continents=" + continents + ", name=" + name + ", regionId=" + regionId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((continents == null) ? 0 : continents.hashCode());
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
