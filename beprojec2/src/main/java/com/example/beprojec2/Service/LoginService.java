package com.example.beprojec2.Service;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Role;
import com.example.beprojec2.Payload.Request.SignUpRequest;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Service.Imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ResponData checkLogin(String username, String password) {
        ResponData responData = new ResponData();
        if(!accountReponsitory.existsByUsername(username)){
            responData.setSuccess(false);
            responData.setExist(true);
            responData.setDescription("Tai khoan khong ton tai");
            return responData;
        }
        Account account = accountReponsitory.findByUsername(username);
        if(!passwordEncoder.matches(password, account.getPassword())){
            responData.setSuccess(false);
            responData.setExist(true);
            responData.setDescription("Sai Mat Khau");
            return responData;
        }
        responData.setSuccess(true);
        responData.setExist(false);
        responData.setDescription("Dang Nhap Thanh Cong");
        return responData;
    }
    @Override
    public ResponData addAccount(SignUpRequest signUpRequest) {
        ResponData responData = new ResponData();
        System.out.println(signUpRequest.getUsername()+ signUpRequest.getPassword() + signUpRequest.getFullname());
        if(signUpRequest.getUsername() == null || signUpRequest.getPassword() == null || signUpRequest.getFullname() == null){
            responData.setSuccess(false);
            responData.setExist(true);
            responData.setDescription("Vui long nhap day du thong tin");
            return responData;
        }
        if(accountReponsitory.existsByUsername(signUpRequest.getUsername())){
            responData.setSuccess(false);
            responData.setExist(true);
            responData.setDescription("tai khoan da ton tai");
            return responData;
        }
        Account account = new Account();
        account.setUsername(signUpRequest.getUsername());
        account.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        account.setFullname(signUpRequest.getFullname());
        Role role=new Role();
        role.setRoleid(signUpRequest.getRoleid());
        account.setRole(role);
        account.setStartdate(signUpRequest.getStartdate());
        try {
            accountReponsitory.save(account);
            responData.setSuccess(true);
            responData.setExist(false);
            responData.setDescription("success");
            return responData;
        }catch (Exception e){
            responData.setSuccess(false);
            responData.setExist(true);
            responData.setDescription("fail");
            return responData;
        }
    }
}
