package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean 등록이 된다.
// 따라서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // JPA Naming 전략
    // Select * from user Where username = ? and password = ?;
//    User findByUsernameAndPassword(String username,String password);

//    @Query(value="Select * from user Where username = ? and password = ?",nativeQuery = true)
//    User login(String username, String password);
}
