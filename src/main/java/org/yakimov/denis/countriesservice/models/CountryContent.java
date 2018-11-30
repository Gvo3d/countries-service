package org.yakimov.denis.countriesservice.models;

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
    private String id;
    private String countryName;
    private String message;
    private Status status;
    private String fileName;
    private String fileContent;
    private String archiveName;
    private Date requestDate;
}
