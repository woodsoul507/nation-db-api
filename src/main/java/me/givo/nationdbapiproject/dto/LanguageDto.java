package me.givo.nationdbapiproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LanguageDto {
    @EqualsAndHashCode.Include
    private Integer languageId;

    @NotBlank
    @Size(max = 50)
    private String language;

    public LanguageDto(@NotBlank
    @Size(max = 100) String language) {
        this.language = language;
    }
    
}
