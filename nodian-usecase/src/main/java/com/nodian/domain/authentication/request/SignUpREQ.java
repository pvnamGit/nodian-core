package com.nodian.domain.authentication.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpREQ {
    private String email;
    private String password;
    private String confirmPassword;
}
