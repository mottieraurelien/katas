package com.example.h2;

import com.example.h2.user.User;
import com.example.h2.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {

        log.info("-------------------------------");
        log.info("Adding Harry as Admin");
        log.info("-------------------------------");
        User harry = new User("Harry", "Admin");
        userRepository.save(harry);
        log.info("Inserted Harry" + harry);

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }

    }

}
