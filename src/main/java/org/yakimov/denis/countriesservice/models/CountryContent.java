package org.yakimov.denis.countriesservice.models;

import lombok.Data;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "country_data")
public class CountryContent {
    @Id
    private Long id;
    private String countryName;
    private Status status;
    private Date requestDate;
    private FileData fileData;
}
