package booksProject.googlebooks.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBook {

    private String id;
    private VolumeInfo volumeInfo;
}


