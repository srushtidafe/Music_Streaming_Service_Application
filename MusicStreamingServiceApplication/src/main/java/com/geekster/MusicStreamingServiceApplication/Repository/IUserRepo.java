package com.geekster.MusicStreamingServiceApplication.Repository;

import com.geekster.MusicStreamingServiceApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User ,Long> {

    User findFirstByUserEmail(String newEmail);

}
