package org.yakimov.denis.countriesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(exclude = {"fileContents"})
public class FileDto {
    private String fileName;
    private String fileContents;
    private String archiveName;
}
