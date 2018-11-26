package org.yakimov.denis.countriesservice.models;

import lombok.Data;

import java.util.Date;

@Data
public class CountryContent extends IdentifiedEntity<Long>{
    private String countryName;
    private Status status;
    private Date requestDate;
}
