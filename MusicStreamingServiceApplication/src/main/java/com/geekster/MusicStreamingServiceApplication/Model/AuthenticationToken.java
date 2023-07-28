package com.geekster.MusicStreamingServiceApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    //mapping
    @OneToOne
    @JoinColumn(name = "fk_user_Id")
    User user;

    @OneToOne
    @JoinColumn(name = "fk_admin_Id")
    Admin admin;
    public AuthenticationToken(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }
    public AuthenticationToken(Admin admin)
    {
        this.admin = admin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }
}
