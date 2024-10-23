package ar.edu.unnoba.poo2024.allmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{
    @Query(value = "SELECT u FROM users u WHERE u.username = :username")
    public User findByUsername(@Param("username") String username);
}
