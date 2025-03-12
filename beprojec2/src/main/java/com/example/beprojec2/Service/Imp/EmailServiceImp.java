package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.Payload.ResponData;

public interface EmailServiceImp {
        ResponData validateCode(String username, String code);
        ResponData sendmailForgotPassword(String username,String code);

}
