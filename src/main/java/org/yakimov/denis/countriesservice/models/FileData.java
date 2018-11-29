package org.yakimov.denis.countriesservice.models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "files")
public class FileData {
    @Id
    private Long id;
    private String fileName;
    private String fileContent;
    private ZipArchive archive;
}
