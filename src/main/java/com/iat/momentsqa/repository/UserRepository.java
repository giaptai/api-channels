package com.iat.momentsqa.repository;

import com.iat.momentsqa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Long> {

    @Query
    Optional<User> findByEmail(String email);
}
