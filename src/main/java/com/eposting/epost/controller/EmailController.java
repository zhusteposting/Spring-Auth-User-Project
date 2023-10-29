package com.eposting.epost.controller;

import com.eposting.epost.model.Email;
import com.eposting.epost.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Email> getAllEmail() {
        return emailService.getAll();
    }
}