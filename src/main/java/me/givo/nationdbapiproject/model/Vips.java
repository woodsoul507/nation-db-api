package me.givo.nationdbapiproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vips")
public class Vips {

    @Id
    @Column(name = "vip_id", length = 11, nullable = false)
    private Integer vipId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vip_id) {
        this.vipId = vip_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vips [name=" + name + ", vip_id=" + vipId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((vipId == null) ? 0 : vipId.hashCode());
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
        Vips other = (Vips) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (vipId == null) {
            if (other.vipId != null)
                return false;
        } else if (!vipId.equals(other.vipId))
            return false;
        return true;
    }

}
