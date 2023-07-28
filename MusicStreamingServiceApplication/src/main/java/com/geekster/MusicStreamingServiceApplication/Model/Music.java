package com.geekster.MusicStreamingServiceApplication.Model;

import com.geekster.MusicStreamingServiceApplication.Model.Enum.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    @NotBlank
    private String musicName;

    @Column(name="artist")
    private String artist;

    @Column(name="duration")
    private double duration;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
}
