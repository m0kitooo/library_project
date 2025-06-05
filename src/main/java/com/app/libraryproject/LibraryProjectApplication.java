package com.app.libraryproject;

import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.service.BookService;
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

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            BookService bookService = ctx.getBean(BookService.class);
            BookRepository bookRepository = ctx.getBean(BookRepository.class);

            bookService.registerBook(new CreateBookRequest("xpp", "jglagja"));

            System.out.println("--------------------------------------");
            System.out.println(bookRepository.findByTitle("xpp").orElseThrow().toBookResponse());
            System.out.println("--------------------------------------");
        };
    }
}
