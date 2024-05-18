package com.nodian.domain.authentication.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRESP {
    private String token;
}
