package org.yakimov.denis.countriesservice.models;

import lombok.Data;

import lombok.ToString;

@Data
@ToString(exclude = {"fileContent"})
public class FileData {
    private String fileName;
    private String fileContent;
    private ZipArchive archive;
}
