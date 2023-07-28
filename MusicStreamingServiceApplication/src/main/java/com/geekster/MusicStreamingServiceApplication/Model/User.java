package com.geekster.MusicStreamingServiceApplication.Model;

import com.geekster.MusicStreamingServiceApplication.Model.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String userName;

    @NotNull
    private Integer userAge;

    @Pattern(regexp = "^.+@(?![Ii][Nn][Ss][Tt][Aa][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    @Column(unique = true)
    private String userEmail;

    @NotBlank
    private String userPassword;

    @Column(name = "playList")
    private List<Long> playList;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}
