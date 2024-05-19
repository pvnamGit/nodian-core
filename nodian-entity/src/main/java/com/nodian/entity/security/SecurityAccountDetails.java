package com.nodian.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nodian.entity.account.Account;
import com.nodian.entity.account.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityAccountDetails implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    @Getter
    private String email;

    @JsonIgnore
    private String password;
    @Getter
    private Long userId;
    @Getter
    private Long accountId;
    @Getter
    private ERole authority;

    private Collection<? extends GrantedAuthority> authorities;

    public static SecurityAccountDetails build(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (account.getAuthority()) {
            case ADMIN -> authorities.add(new SimpleGrantedAuthority(ERole.ADMIN.name()));
            case USER -> authorities.add(new SimpleGrantedAuthority(ERole.USER.name()));
        }
        var email = account.getEmail();
        var password = account.getPassword();
        var userId = account.getUser().getId();
        var accountId = account.getId();
        var authority = account.getAuthority();
        return new SecurityAccountDetails(email, password, userId, accountId, authority, authorities);
    }

    public static SecurityAccountDetails buildCurrentUser(SecurityAccountDetails accountDetail) {
        return SecurityAccountDetails.builder()
                .accountId(accountDetail.accountId)
                .userId(accountDetail.userId)
                .email(accountDetail.getEmail())
                .authority(accountDetail.getAuthority())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
