package me.givo.nationdbapiproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CountryLanguagesId implements Serializable {

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "language_id")
    private Integer languageId;

    public CountryLanguagesId() {
    }

    public CountryLanguagesId(Integer countryId, Integer languageId) {
        this.countryId = countryId;
        this.languageId = languageId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "CountryLanguagesId [countryId=" + countryId + ", languageId=" + languageId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
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
        CountryLanguagesId other = (CountryLanguagesId) obj;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (languageId == null) {
            if (other.languageId != null)
                return false;
        } else if (!languageId.equals(other.languageId))
            return false;
        return true;
    }

}
