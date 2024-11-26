package com.portafolio.portfolio_backend.controller;

import com.portafolio.portfolio_backend.dto.EmailRequest;
import com.portafolio.portfolio_backend.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(
                emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getBodyText()
        );
    }
}