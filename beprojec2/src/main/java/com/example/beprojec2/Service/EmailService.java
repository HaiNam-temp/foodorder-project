package com.example.beprojec2.Service;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Service.Imp.EmailServiceImp;
import com.example.beprojec2.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService   implements EmailServiceImp {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    MailUtil mailUtil;
    //constructor injection
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String generateResetCode(String username) {
        Random random = new Random();
        String resetCode = String.format("%06d", random.nextInt(1000000));
        Account account=accountReponsitory.findByUsername(username);
            account.setCode(resetCode);
            account.setCodecreatedate(Timestamp.valueOf(LocalDateTime.now()));
            accountReponsitory.save(account);
        return resetCode;
    }
    @Async
    public ResponData sendmailForgotPassword(String username, String code) {
        ResponData responData = new ResponData();
        try {

            Account account = accountReponsitory.findByUsername(username);

            String subject = "Mã xác nhận đặt lại mật khẩu FoodDelta";
            String content = """
                    Xin chào [[name]],<br>
                    Đây là mã xác thực tạm thời để đặt lại mật khẩu của bạn. Vui lòng không chia sẻ mã này!
                    <br><h4>Mã xác thực: [[code]]</h4><br>
                    """;
            content = content.replace("[[name]]", account.getFullname());
            content = content.replace("[[code]]", code);
            responData.setSuccess(mailUtil.sendEmail(username, subject, content));
            responData.setExist(false);
        } catch (Exception e) {
            responData.setSuccess(false);
            responData.setExist(true);
            System.out.println("Error send forgot password "+e.getMessage());
        }
        return responData;
    }

    @Override
    public ResponData validateCode(String username, String code) {
        ResponData responData = new ResponData();
        Account account=accountReponsitory.findByUsername(username);
        if(account==null){
            throw ApiException.ErrBadCredentials().build();
        }
            if(code.equals(account.getCode())){
                Timestamp createCodeAt = account.getCodecreatedate();
                if(createCodeAt.toLocalDateTime().plusMinutes(10).isAfter(LocalDateTime.now())){
                    responData.setSuccess(true);
                    responData.setExist(false);
                }
                else{
                    responData.setSuccess(false);
                    responData.setExist(false);
                    responData.setData("Code has expired");
                }
            }
            else{
                responData.setSuccess(false);
                responData.setExist(false);
                responData.setData("Invalid code");
            }
        return responData;
    }
}
