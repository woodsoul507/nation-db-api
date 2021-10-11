package me.givo.nationdbapiproject.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region_areas")
public class RegionAreas {

    @Id
    @Column(name = "region_name", length = 100, nullable = false)
    private String regionName;

    @Column(name = "region_area", nullable = false)
    private BigDecimal regionArea;

    public RegionAreas() {
    }

    public RegionAreas(String regionName, BigDecimal regionArea) {
        this.regionName = regionName;
        this.regionArea = regionArea;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigDecimal getRegion_area() {
        return regionArea;
    }

    public void setRegion_area(BigDecimal regionArea) {
        this.regionArea = regionArea;
    }

    @Override
    public String toString() {
        return "RegionAreas [regionArea=" + regionArea + ", regionName=" + regionName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regionArea == null) ? 0 : regionArea.hashCode());
        result = prime * result + ((regionName == null) ? 0 : regionName.hashCode());
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
        RegionAreas other = (RegionAreas) obj;
        if (regionArea == null) {
            if (other.regionArea != null)
                return false;
        } else if (!regionArea.equals(other.regionArea))
            return false;
        if (regionName == null) {
            if (other.regionName != null)
                return false;
        } else if (!regionName.equals(other.regionName))
            return false;
        return true;
    }

}
