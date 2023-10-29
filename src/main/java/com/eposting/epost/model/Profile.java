package com.eposting.epost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String preferences;
    private String skills;
}
