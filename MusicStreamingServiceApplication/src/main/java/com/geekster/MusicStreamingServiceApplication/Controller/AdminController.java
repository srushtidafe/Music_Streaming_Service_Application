package com.geekster.MusicStreamingServiceApplication.Controller;

import com.geekster.MusicStreamingServiceApplication.Model.Admin;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignInInput;
import com.geekster.MusicStreamingServiceApplication.Model.Dto.SignUpOutput;
import com.geekster.MusicStreamingServiceApplication.Model.Music;
import com.geekster.MusicStreamingServiceApplication.Model.User;
import com.geekster.MusicStreamingServiceApplication.Service.AdminService;
import com.geekster.MusicStreamingServiceApplication.Service.MusicService;
import com.geekster.MusicStreamingServiceApplication.Service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Validated
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MusicService musicService;

    @PostMapping("Admin/Signup")
    public SignUpOutput signUp(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        return adminService.signUp(admin);
    }

    @PostMapping("Admin/SignIn")
    public String signIn(@RequestBody @Valid SignInInput signInInput) throws NoSuchAlgorithmException {
        return adminService.signIn(signInInput);
    }

    @GetMapping("Music")
    public List<Music> getAllMusic(){
        return musicService.getAllMusic();
    }
    @PostMapping("Music")
    public String addMusic(@RequestBody @Valid Music music ) {
        return musicService.addMusic(music);

    }
    @PutMapping("Music/{id}/{artist}")
    public String updateMusicById(@PathVariable Long id ,@PathVariable String artist,@PathVariable String email, @PathVariable String token){
        if(tokenService.authenticate(email,token)){
            return musicService.updateMusicById(id ,artist , email);
        }
        return "Authentication Failed";

    }
    @DeleteMapping("Music/{id}")
    public String deleteMusicById(@PathVariable Long id){
        return musicService.deleteMusic(id);
    }
}

