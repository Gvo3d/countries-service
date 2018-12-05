package org.yakimov.denis.countriesservice.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArchiveDto {
    private String identity;
    private String fileName;
    private String fileContents;
}
