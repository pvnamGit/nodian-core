package com.nodian.domain.authentication.facade;

import com.nodian.domain.authentication.request.SignInREQ;
import com.nodian.domain.authentication.request.SignUpREQ;
import com.nodian.domain.authentication.response.AuthenticationRESP;

public interface AuthenticationFacade {
    void registerAccount(SignUpREQ signUpRequest);
    AuthenticationRESP signIn(SignInREQ req);
}
