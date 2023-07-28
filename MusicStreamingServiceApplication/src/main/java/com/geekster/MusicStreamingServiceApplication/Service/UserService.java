package com.geekster.MusicStreamingServiceApplication.Service;

import com.geekster.MusicStreamingServiceApplication.Model.AuthenticationToken;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignInInput;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignUpOutput;
import com.geekster.MusicStreamingServiceApplication.Model.Music;
import com.geekster.MusicStreamingServiceApplication.Model.User;
import com.geekster.MusicStreamingServiceApplication.Repository.IMusicRepo;
import com.geekster.MusicStreamingServiceApplication.Repository.ITokenRepo;
import com.geekster.MusicStreamingServiceApplication.Repository.IUserRepo;
import com.geekster.MusicStreamingServiceApplication.Service.Hashing.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    ITokenRepo tokenRepo;
    @Autowired
    IMusicRepo musicRepo;

    public SignUpOutput signUp(User user)throws NoSuchAlgorithmException {

        String userEmail = user.getUserEmail();

        User user1 = userRepo.findFirstByUserEmail(userEmail);

        if(user1 != null){
            return new SignUpOutput(false ,"Email Already registered....!!!");
        }
        String hexPassWord = Encrypt.encryptPassword(user.getUserPassword());
        user.setUserPassword(hexPassWord);

        userRepo.save(user);
        return new SignUpOutput(true ,"Sign Up SuccessFull....!!!");
    }

    public String signIn(SignInInput signInInput) throws NoSuchAlgorithmException {

        String email = signInInput.getEmail();

        String password = signInInput.getPassword();

        if(email == null) {
            return "Invalid credentials...!!!";
        }
        User user1 = userRepo.findFirstByUserEmail(email);

        if(user1 == null) return "Email not exist sign Up first...!!!";
        if(!Encrypt.encryptPassword(password).equals(user1.getUserPassword()))return "Invalid Sign In credentials...!!";

        AuthenticationToken authTokenUser = new AuthenticationToken(user1);
        tokenRepo.save(authTokenUser);

        return "Signed In Successfully...!!!";
    }

    public String logOut(String email) {
        User user = userRepo.findFirstByUserEmail(email);

        AuthenticationToken authToken = tokenRepo.findFirstByUser(user);
        tokenRepo.delete(authToken);

        return "Logged Out.....!!!!";
    }

    public List<Music> getPlayList(Long id)throws NoSuchAlgorithmException {
        List<Music> musicList = new ArrayList<>();

        User musicUser = userRepo.findById(id).get();
        List<Long> arrayList = musicUser.getPlayList();

        for(int idx = 0 ; idx < arrayList.size() ; idx++) {

            Music music= musicRepo.findById(arrayList.get(idx)).get();
            musicList.add(music);
        }
        return musicList;
    }

    public String deleteMusic(Long id, Long musicId) throws NoSuchAlgorithmException{
        User musicUser = userRepo.findById(id).get();
        List<Long> arrayList=musicUser.getPlayList()
                ;
        for(int jdx = 0 ; jdx < arrayList.size() ; jdx++) {

            Long id1 = arrayList.get(jdx);
            if(id1.equals( musicId)) {
                arrayList.remove(jdx);
            }
        }
        return "music deleted form playlist";
    }


}