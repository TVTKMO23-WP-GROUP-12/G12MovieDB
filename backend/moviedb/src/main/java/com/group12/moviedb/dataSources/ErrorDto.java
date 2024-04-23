package com.group12.moviedb.dataSources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Builder
@Data
public class ErrorDto {
    
    private String message;

    public String getMessage(String message) {
        return message;
    }
}
