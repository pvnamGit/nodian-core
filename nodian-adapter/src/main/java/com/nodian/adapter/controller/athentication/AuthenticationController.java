package com.nodian.adapter.controller.athentication;

import com.nodian.adapter.shared.BaseEntityResponse;
import com.nodian.domain.authentication.facade.AuthenticationFacade;
import com.nodian.domain.authentication.request.SignInREQ;
import com.nodian.domain.authentication.request.SignUpREQ;
import com.nodian.domain.authentication.response.AuthenticationRESP;
import com.nodian.entity.account.Account;
import com.nodian.entity.account.AuthenticationProvider;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${url.prefix}/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationFacade authenticationFacade;
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseEntityResponse signUp(@RequestBody @Valid SignUpREQ req) {
        authenticationFacade.registerAccount(req);
        return BaseEntityResponse.success();
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public BaseEntityResponse signIn(@RequestBody @Valid SignInREQ req) {
        AuthenticationRESP response = authenticationFacade.signIn(req);
        return BaseEntityResponse.success(response);
    }
}
