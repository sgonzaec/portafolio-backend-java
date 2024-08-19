package com.portafolio.portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {

    private final SesClient sesClient;

    public EmailService(
            @Value("${aws.access.key.id}") String accessKeyId,
            @Value("${aws.secret.access.key}") String secretAccessKey,
            @Value("${aws.region}") String region) {
        this.sesClient = SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .region(Region.of(region))
                .build();
    }

    public void sendEmail(String from, String to, String subject, String bodyText) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content contentSubject = Content.builder()
                .data(subject)
                .build();

        Content contentBody = Content.builder()
                .data(bodyText)
                .build();

        Body body = Body.builder()
                .text(contentBody)
                .build();

        Message message = Message.builder()
                .subject(contentSubject)
                .body(body)
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .source(from)
                .destination(destination)
                .message(message)
                .build();

        SendEmailResponse response = sesClient.sendEmail(request);
        System.out.println("Email sent! Message ID: " + response.messageId());
    }
}