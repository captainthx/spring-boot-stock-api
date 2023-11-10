package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
