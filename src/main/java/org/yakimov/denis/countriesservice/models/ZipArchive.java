package org.yakimov.denis.countriesservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class ZipArchive {
    private String archiveName;
}
