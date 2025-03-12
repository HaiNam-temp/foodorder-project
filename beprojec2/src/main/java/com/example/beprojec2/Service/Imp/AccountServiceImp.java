package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.DTO.AccountDTO;
import com.example.beprojec2.Payload.Request.CartRequest;
import com.example.beprojec2.Payload.Request.SignUpRequest;
import com.example.beprojec2.Payload.ResponData;

import java.util.List;

public interface AccountServiceImp {
    AccountDTO accountDetails(String username);
    ResponData sendVerifyCode(String username);
    ResponData resetPassword(String username, String code, String newPassword);
    ResponData changePassword(String username, String oldPassword, String newPassword);
}
