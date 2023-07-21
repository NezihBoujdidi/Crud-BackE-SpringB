package com.example.crud.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query("""
select t from Token t inner join Credentials c on t.user.id = c.id where c.id= :userId and (t.expired=false or t.revoked=false ) 
""")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);

}
