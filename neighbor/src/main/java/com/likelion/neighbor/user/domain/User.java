package com.likelion.neighbor.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_SIGN_UP_ID", nullable = false)
    private String signUpId;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_IDENTITY", nullable = false)
    private String identity;

    @Column(name = "USER_BIRTH_DATE", nullable = false)
    private String birthDate;

    @Column(name = "USER_TELECOM", nullable = false)
    private String telecom;

    @Column(name = "USER_PHONE_NO", nullable = false)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;
}