package com.geekster.MusicStreamingServiceApplication.Service;

import com.geekster.MusicStreamingServiceApplication.Model.AuthenticationToken;
import com.geekster.MusicStreamingServiceApplication.Repository.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepo tokenRepo;

    public boolean authenticate(String email, String tokenValue)
    {
        AuthenticationToken authToken = tokenRepo.findFirstByTokenValue(tokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenEmail = authToken.getUser().getUserEmail();

        return tokenEmail.equals(email);
    }
}
