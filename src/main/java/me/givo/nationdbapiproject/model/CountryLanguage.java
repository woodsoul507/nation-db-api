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
public class CountryLanguage {

    @EmbeddedId
    private CountryLanguageId id;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @MapsId("countryId")
    @JoinColumn(name = "country_id")
    private Country countries;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @MapsId("languageId")
    @JoinColumn(name = "language_id")
    private Language languages;

    @Column(name = "official", length = 1, nullable = false)
    private Integer official;

    public CountryLanguage() {
    }

    public CountryLanguage(Country countries, Language languages, Integer official) {
        this.id = new CountryLanguageId(countries.getCountryId(), languages.getLanguageId());
        this.countries = countries;
        this.languages = languages;
        this.official = official;
    }

    public CountryLanguageId getId() {
        return id;
    }

    public void setId(CountryLanguageId id) {
        this.id = id;
    }

    public Country getCountries() {
        return countries;
    }

    public void setCountries(Country countries) {
        this.countries = countries;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
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

    public CountryLanguage(CountryLanguageId id, Country countries, Language languages, Integer official) {
        this.id = id;
        this.countries = countries;
        this.languages = languages;
        this.official = official;
    }

}
