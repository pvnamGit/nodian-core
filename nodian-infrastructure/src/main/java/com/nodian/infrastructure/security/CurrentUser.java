package com.nodian.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUser {
//
//    private final AccountRepository accountRepository;
//
//    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserEmail = authentication.getName();
//            Account account = accountRepository.findByEmail(currentUserEmail).orElse(null);
//            return account.getUser();
//        }
//        return null;
//    }

}
