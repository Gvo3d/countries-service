package org.yakimov.denis.countriesservice.models;

import lombok.Data;

import java.util.Date;

@Data
public class IdentifiedEntity<ID> {
    private ID id;
    private Date created;
}
