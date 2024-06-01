package com.toy.techblog.domain.user.repository;

import com.toy.techblog.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndAccountType(String email, String provider);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByPrivateAccess(String access);

    boolean existsByNickname(String nickname);
}
