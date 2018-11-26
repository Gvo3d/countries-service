package org.yakimov.denis.countriesservice.models;

import lombok.Data;

@Data
public class FileData extends IdentifiedEntity<Long> {
    private String fileName;
    private String fileContent;
}
