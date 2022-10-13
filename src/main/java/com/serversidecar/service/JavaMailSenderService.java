package com.serversidecar.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.serversidecar.model.dto.request.EmailRequest;

@Service
public class JavaMailSenderService {

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    public JavaMailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public String HtmlMail(String text, String name, String position) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("mailtext", text);
        variables.put("name", name);
        variables.put("position", position);

        final String templateFileName = "mail";
        String output = this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));

        return output;
    }

    public EmailRequest sendHtmlEmailRequest(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getKepada());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());

        mailSender.send(message);
        System.out.println("Mail Sent . . . . . .");
        return emailRequest;
    }

}
