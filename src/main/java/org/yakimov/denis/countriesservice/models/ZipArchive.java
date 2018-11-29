package org.yakimov.denis.countriesservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "zip")
public class ZipArchive {
    @Id
    private Long id;
    private String archiveName;
}
