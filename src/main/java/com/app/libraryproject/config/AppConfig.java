package com.app.libraryproject.config;

import com.app.libraryproject.mapper.LibraryPaymentMapper;
import com.app.libraryproject.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public LibraryPaymentMapper libraryPaymentMapper(UserRepository userRepository) {
        return new LibraryPaymentMapper(userRepository);
    }
}
