package me.givo.nationdbapiproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country_languages")
public class CountryLanguages {

    @Id
    @Column(name = "country_id", length = 11, nullable = false)
    private Integer countryId;

    @Column(name = "language_id", length = 11, nullable = false)
    private Integer languageId;

    @Column(name = "official", length = 1, nullable = false)
    private Integer official;

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

    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    @Override
    public String toString() {
        return "CountryLanguages [country_id=" + countryId + ", language_id=" + languageId + ", official=" + official
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
        result = prime * result + ((official == null) ? 0 : official.hashCode());
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
        CountryLanguages other = (CountryLanguages) obj;
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
        if (official == null) {
            if (other.official != null)
                return false;
        } else if (!official.equals(other.official))
            return false;
        return true;
    }

}
