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
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", length = 11, nullable = false)
    private Integer languageId;

    @Column(name = "language", length = 50, nullable = false)
    private String language;

    @OneToMany(mappedBy = "languages", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CountryLanguage> countryLanguages = new HashSet<CountryLanguage>();

    public Language() {
    }

    public Language(String language) {
        this.language = language;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<CountryLanguage> getCountryLanguages() {
        return countryLanguages;
    }

    public void setCountryLanguages(Set<CountryLanguage> countryLanguages) {
        this.countryLanguages.addAll(countryLanguages);
    }

    @Override
    public String toString() {
        return "Languages [countryLanguages=" + countryLanguages + ", language=" + language + ", languageId="
                + languageId + "]";
    }

    public Language(Integer languageId, String language, Set<CountryLanguage> countryLanguages) {
        this.languageId = languageId;
        this.language = language;
        this.countryLanguages = countryLanguages;
    }

}
