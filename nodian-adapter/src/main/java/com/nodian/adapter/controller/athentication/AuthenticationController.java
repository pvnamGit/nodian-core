package com.nodian.adapter.controller.athentication;

import com.nodian.adapter.shared.BaseEntityResponse;
import com.nodian.entity.account.Account;
import com.nodian.entity.account.AuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @GetMapping("/test")
    public BaseEntityResponse test() {
        Account account = Account.builder().email("test@gmail.com").authenticationProvider(AuthenticationProvider.NONE).build();
        return BaseEntityResponse.success(account);
    }
}
