package com.geekster.MusicStreamingServiceApplication.Repository;

import com.geekster.MusicStreamingServiceApplication.Model.AuthenticationToken;
import com.geekster.MusicStreamingServiceApplication.Model.Music;
import com.geekster.MusicStreamingServiceApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITokenRepo extends JpaRepository< AuthenticationToken , Long> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);

    AuthenticationToken findFirstByUser(User user);

    List<Music> findByUser(User user);
}
