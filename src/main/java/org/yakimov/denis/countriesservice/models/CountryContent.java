package org.yakimov.denis.countriesservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Date;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "country_data")
@ToString(exclude = {"fileContent"})
public class CountryContent {
    @Id
    @JsonIgnore
    private String id;
    private String countryName;
    private String countryCode;
    @JsonIgnore
    private String message;
    private Status status;
    private String fileName;
    @JsonIgnore
    private String fileContent;
    @JsonIgnore
    private String archiveName;
    @JsonIgnore
    private Date requestDate;
}
