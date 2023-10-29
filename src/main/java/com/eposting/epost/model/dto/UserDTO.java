package com.eposting.epost.model.dto;

import com.eposting.epost.enumuation.AccountStatus;
import com.eposting.epost.enumuation.AuthProvider;
import com.eposting.epost.enumuation.Role;
import com.eposting.epost.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    private String email;

    private String signupMethod;

    private Date signupDate;

    private Boolean isEmailAuthenticated;

    private Integer accountType;

    private AccountStatus accountStatus;

    private Role role;

    private String accountSettings;

    private Date lastActive;

    private AuthProvider provider;

    private ProfileDTO profile;
}
