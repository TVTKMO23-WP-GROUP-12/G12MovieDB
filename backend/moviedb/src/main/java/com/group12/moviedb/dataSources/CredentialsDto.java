package com.group12.moviedb.dataSources;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDto {

    private String login;
    private char[] password;


    public String getLogin() {    
    return login;
    }

    public String getPassword() {    
        return new String(password);
        }

}
