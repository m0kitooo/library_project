package com.app.libraryproject;

import com.app.libraryproject.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class LibraryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return (args -> {
//            EventService eventService = ctx.getBean(EventService.class);
//            eventService.decideProposal(null);
//        });
//    }
}
