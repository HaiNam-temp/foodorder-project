package com.example.beprojec2.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String toEmail, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();// tạo 1 mail
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);// điền thông tin vào mail

        try {
            helper.setFrom("fooddelivery8886@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(mimeMessage); // gửi email
            return true;
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
