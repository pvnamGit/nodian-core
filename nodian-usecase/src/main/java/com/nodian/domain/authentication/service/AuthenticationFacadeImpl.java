package com.nodian.domain.authentication.service;

import com.nodian.domain.authentication.facade.AuthenticationFacade;
import com.nodian.domain.authentication.request.SignInREQ;
import com.nodian.domain.authentication.request.SignUpREQ;
import com.nodian.domain.authentication.response.AuthenticationRESP;
import com.nodian.domain.jwt.JwtService;
import com.nodian.entity.security.SecurityAccountDetails;
import com.nodian.entity.account.Account;
import com.nodian.entity.account.AccountRepository;
import com.nodian.entity.account.AuthenticationProvider;
import com.nodian.entity.account.ERole;
import com.nodian.entity.user.User;
import com.nodian.entity.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @SneakyThrows
    public void registerAccount(SignUpREQ signUpRequest) {
        Account existedAccount = accountRepository.findByEmail(signUpRequest.getEmail());
        if (existedAccount != null) throw new ConstraintViolationException("Email is already registered", null);
        Account account = Account.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .authenticationProvider(AuthenticationProvider.NONE)
                .authority(ERole.USER)
                .build();
        accountRepository.persistAndFlush(account);
        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .account(account)
                .build();
        userRepository.persist(user);
    }

    @Override
    @SneakyThrows
    public AuthenticationRESP signIn(SignInREQ req) {
        Account account = accountRepository.findByEmail(req.getEmail());
        if (account == null) throw new EntityNotFoundException();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        SecurityAccountDetails accountDetails = SecurityAccountDetails.build(account);
        String token = jwtService.generateToken(accountDetails);
        return AuthenticationRESP.builder().token(token).build();
    }
}
