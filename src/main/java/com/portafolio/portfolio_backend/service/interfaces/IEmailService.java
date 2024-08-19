package com.portafolio.portfolio_backend.service.interfaces;

import com.portafolio.portfolio_backend.dto.EmailRequest;

public interface IEmailService {
    void sendEmail(EmailRequest emailRequest);
}