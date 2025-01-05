package com.javapulses.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {

        this.jwt = jwt;

    }


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


}

