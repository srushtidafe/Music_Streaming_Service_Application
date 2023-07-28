package com.geekster.MusicStreamingServiceApplication.Service;

import com.geekster.MusicStreamingServiceApplication.Model.Music;
import com.geekster.MusicStreamingServiceApplication.Model.User;
import com.geekster.MusicStreamingServiceApplication.Repository.IMusicRepo;
import com.geekster.MusicStreamingServiceApplication.Repository.ITokenRepo;
import com.geekster.MusicStreamingServiceApplication.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MusicService {
    @Autowired
    IMusicRepo musicRepo;
    @Autowired
    IUserRepo userRepo;
    @Autowired
    ITokenRepo tokenRepo;
    public String deleteMusic(Long id) {
         musicRepo.deleteById(id);
         return "Music deleted form portal...!!";
    }

    public String addMusic(Music music) {
        musicRepo.save(music);
        return "added...!!!";

    }

    public List<Music> getAllMusic() {
        return musicRepo.findAll();
    }
    public String updateMusicById(Long id,String artist ,String email) {
        User user = userRepo.findFirstByUserEmail(email);
        List<Music> musicList = tokenRepo.findByUser(user);
        for(Music music : musicList){
            if(music.getMusicId().equals(id)){
                music.setArtist(artist);
                musicRepo.save(music);
                return "Update SuccessFully";
            }
        }
        return "Unknown Error Occurred";

    }
}
