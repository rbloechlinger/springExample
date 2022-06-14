package com.romeone.springapp;

import com.romeone.springapp.model.Employee;
import com.romeone.springapp.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringApplication {
    @Autowired
    EmployeeRepository repository;

    private final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

//    @Bean
//    CommandLineRunner initDatabase(EmployeeRepository repository) {
//        return args -> {
//            logger.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
//            logger.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
//        };
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        // TODO: Sample data
        logger.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
        logger.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
    }
}
