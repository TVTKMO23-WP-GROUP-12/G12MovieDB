package com.group12.moviedb.dataSources;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data


public class SignUpDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private char[] password;

    @NotEmpty
    private String login;


    public String getPassword() {
        return new String(password);
    }

}


