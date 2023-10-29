package booksProject.googlebooks.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    private String title;

    private List<String> autors;

    private String publisher;

    private String description;

    @JsonProperty("industryIdentifiers")
    private List<IndustryIdentifiers> isbnList;

    private String language;
}
