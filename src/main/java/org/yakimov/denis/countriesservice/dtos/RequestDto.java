package org.yakimov.denis.countriesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.yakimov.denis.countriesservice.models.Status;

@Data
@AllArgsConstructor
public class RequestDto {
    private String message;
    private String name;
    private String alpha2Code;
    private Status status;
}
