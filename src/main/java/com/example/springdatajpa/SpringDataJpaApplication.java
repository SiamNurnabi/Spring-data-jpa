package com.example.springdatajpa;

import com.example.springdatajpa.student.Student;
import com.example.springdatajpa.student.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            generateRandomStudents(studentRepository);
//            Sort sort = Sort.by(Sort.Direction.ASC, "firstName");

            Sort sort = Sort.by( "firstName").descending()
                    .and(Sort.by("age").ascending());

            studentRepository.findAll(sort)     //this property name should come from entity class variable
                    .forEach(student -> System.out.println(student.getFirstName()+" "+student.getAge()));
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
