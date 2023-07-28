package com.geekster.MusicStreamingServiceApplication.Service;


import com.geekster.MusicStreamingServiceApplication.Model.Admin;
import com.geekster.MusicStreamingServiceApplication.Model.AuthenticationToken;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignInInput;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignUpOutput;
import com.geekster.MusicStreamingServiceApplication.Repository.IAdminRepo;
import com.geekster.MusicStreamingServiceApplication.Repository.ITokenRepo;
import com.geekster.MusicStreamingServiceApplication.Service.Hashing.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {

    @Autowired
    IAdminRepo adminRepo;
    @Autowired
    ITokenRepo tokenRepo;


    public SignUpOutput signUp(Admin admin) throws NoSuchAlgorithmException {
        String adminEmail = admin.getAdminEmail();

        Admin admin1 = adminRepo.findFirstByAdminEmail(adminEmail);

        if(admin1 != null){
            return new SignUpOutput(false ,"Email Already registered....!!!");
        }
        String hexPassWord = Encrypt.encryptPassword(admin.getAdminPassword());
        admin.setAdminPassword(hexPassWord);

        adminRepo.save(admin);
        return new SignUpOutput(true ,"Sign Up SuccessFull....!!!");
    }

    public String signIn(SignInInput signInInput)throws NoSuchAlgorithmException {
        String adminEmail = signInInput.getEmail();

        String password = signInInput.getPassword();

        if(adminEmail == null) {
            return "Invalid credentials...!!!";
        }
        Admin admin1 = adminRepo.findFirstByAdminEmail(adminEmail);

        if(admin1 == null) return "Email not exist sign Up first...!!!";
        if(!Encrypt.encryptPassword(password).equals(admin1.getAdminPassword()))return "Invalid Sign In credentials...!!";

        AuthenticationToken authTokenAdmin = new AuthenticationToken(admin1);
        tokenRepo.save(authTokenAdmin);

        return "Signed In Successfully...!!!";
    }
}
