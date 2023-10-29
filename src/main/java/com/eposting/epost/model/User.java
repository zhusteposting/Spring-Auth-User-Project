package com.eposting.epost.model;

import com.eposting.epost.enumuation.AccountStatus;
import com.eposting.epost.enumuation.AuthProvider;
import com.eposting.epost.enumuation.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String email;

    private String password;

    private String signupMethod;

    private Date signupDate;

    private Boolean isEmailAuthenticated = false;

    private Integer accountType = 1;

    private AccountStatus accountStatus = AccountStatus.pending;

    private Role role = Role.ROLE_USER;

    private String accountSettings;

    private Date lastActive;

    private AuthProvider provider;

    @DBRef
    private Profile profile;
}
