package me.givo.nationdbapiproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continents")
public class Continents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id", length = 11)
    private Integer continentId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "continents", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Regions> regions = new HashSet<Regions>();

    public Continents() {
    }

    public Continents(String name) {
        this.name = name;
    }

    public Continents(Integer continentId, String name) {
        this.continentId = continentId;
        this.name = name;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continents [continent_id=" + continentId + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((continentId == null) ? 0 : continentId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Continents other = (Continents) obj;
        if (continentId == null) {
            if (other.continentId != null)
                return false;
        } else if (!continentId.equals(other.continentId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
