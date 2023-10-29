package com.eposting.epost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    private String id;
    private String email;
}
