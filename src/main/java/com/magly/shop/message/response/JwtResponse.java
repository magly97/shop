package com.magly.shop.message.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    @Getter @Setter
    private String token;
    @Getter @Setter
    private String type = "Bearer";
    @Getter @Setter
    private String username;
    @Getter
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
    }
}