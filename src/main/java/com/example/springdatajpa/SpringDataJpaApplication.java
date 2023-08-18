package com.example.springdatajpa;

import com.example.springdatajpa.student.Student;
import com.example.springdatajpa.student.StudentIdCard;
import com.example.springdatajpa.student.StudentIdCardRepository;
import com.example.springdatajpa.student.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
//            generateRandomStudents(studentRepository);
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Student student = new Student(
                    firstName,
                    lastName,
                    String.format("%s%s@yopmail.com", firstName, lastName),
                    faker.number().numberBetween(20, 30)
            );
            studentIdCardRepository.save(
                    new StudentIdCard("123456789", student)
            );

            studentRepository.findById(1L)
                    .ifPresent(System.out::println);

            studentIdCardRepository.findById(1L)
                    .ifPresent(System.out::println);
        };
    }

    private void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s%s@yopmail.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(15, 45)
            );
            studentRepository.save(student);
        }
    }

}
