package me.givo.nationdbapiproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "country_languages")
public class CountryLanguages {

    @EmbeddedId
    private CountryLanguagesId id;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @MapsId("countryId")
    @JoinColumn(name = "country_id")
    private Countries countries;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @MapsId("languageId")
    @JoinColumn(name = "language_id")
    private Languages languages;

    @Column(name = "official", length = 1, nullable = false)
    private Integer official;

    public CountryLanguages() {
    }

    public CountryLanguages(Countries countries, Languages languages, Integer official) {
        this.id = new CountryLanguagesId(countries.getCountryId(), languages.getLanguageId());
        this.countries = countries;
        this.languages = languages;
        this.official = official;
    }

    public CountryLanguagesId getId() {
        return id;
    }

    public void setId(CountryLanguagesId id) {
        this.id = id;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    @Override
    public String toString() {
        return "CountryLanguages [countries=" + countries.getName() + ", id=" + id + ", languages="
                + languages.getLanguage() + ", official=" + official + "]";
    }

    public CountryLanguages(CountryLanguagesId id, Countries countries, Languages languages, Integer official) {
        this.id = id;
        this.countries = countries;
        this.languages = languages;
        this.official = official;
    }

}
