package com.eposting.epost.service;

import com.eposting.epost.model.Email;
import com.eposting.epost.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Email createEmail(String email) {
        Email emailModel = new Email();
        emailModel.setEmail(email);
        return emailRepository.save(emailModel);
    }

    public List<Email> getAll() {
        return emailRepository.findAll();
    }
}
