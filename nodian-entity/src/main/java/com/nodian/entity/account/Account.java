package com.nodian.entity.account;

import com.nodian.entity.shared.BaseEntity;
import com.nodian.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.annotation.Nullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
@SQLDelete(sql = "UPDATE accounts SET is_active = false")
public class Account extends BaseEntity {
    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    @Nullable
    private String password;

    @Column(name = "authority")
    private ERole authority;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_provider")
    private AuthenticationProvider authenticationProvider;
}
