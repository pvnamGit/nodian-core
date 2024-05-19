package com.nodian.domain.security;

import com.nodian.entity.account.AccountRepository;
import com.nodian.entity.security.SecurityAccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityCurrentUser {

    private final AccountRepository accountRepository;

    public SecurityAccountDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityAccountDetails account = (SecurityAccountDetails) authentication.getPrincipal();
        if (account == null) return null;
        return SecurityAccountDetails.buildCurrentUser(account);
    }

}
