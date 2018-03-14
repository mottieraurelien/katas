package com.example.h2.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository dedicated to the User representation.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Useful to create specific methods :
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

}
