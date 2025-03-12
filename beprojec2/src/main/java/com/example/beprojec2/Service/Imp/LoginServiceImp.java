package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.Payload.Request.SignUpRequest;
import com.example.beprojec2.Payload.ResponData;

public interface LoginServiceImp {
    ResponData addAccount(SignUpRequest signUpRequest);
    ResponData checkLogin(String username, String password);
}
