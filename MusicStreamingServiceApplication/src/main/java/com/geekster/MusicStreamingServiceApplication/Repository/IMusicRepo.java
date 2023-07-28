package com.geekster.MusicStreamingServiceApplication.Repository;

import com.geekster.MusicStreamingServiceApplication.Model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IMusicRepo extends JpaRepository< Music ,Long> {

}
