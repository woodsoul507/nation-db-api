package me.givo.nationdbapiproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", length = 11, nullable = false)
    private Integer languageId;

    @Column(name = "language", length = 50, nullable = false)
    private String language;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer language_id) {
        this.languageId = language_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Languages [language=" + language + ", language_id=" + languageId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((language == null) ? 0 : language.hashCode());
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
        Languages other = (Languages) obj;
        if (language == null) {
            if (other.language != null)
                return false;
        } else if (!language.equals(other.language))
            return false;
        if (languageId == null) {
            if (other.languageId != null)
                return false;
        } else if (!languageId.equals(other.languageId))
            return false;
        return true;
    }

}
