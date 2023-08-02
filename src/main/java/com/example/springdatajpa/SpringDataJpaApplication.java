package com.example.springdatajpa;

import com.example.springdatajpa.student.Student;
import com.example.springdatajpa.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            Student student1 = new Student(
                    "jarin",
                    "anjum",
                    "jarinanjum@gmail.com",
                    22);
            Student student2 = new Student(
                    "sifat",
                    "nur",
                    "sifatnur@gmail.com",
                    20);
            Student student3 = new Student(
                    "siam",
                    "nurnabi",
                    "siamnurnabi@gmail.com",
                    27);
            List studentList = new ArrayList();
            studentList.add(student1);
            studentList.add(student2);
            studentList.add(student3);
            studentRepository.saveAll(studentList);
            System.out.println("Student count::::::::::" + studentRepository.count());

            studentRepository
                    .findStudentByEmail("sifatnur@gmail.com")
                    .ifPresentOrElse(student -> {
                        System.out.println(student);
                    }, () -> {
                        System.out.println("Student with email sifatnur@gmail.com not found");
                    });
            System.out.println("without jpql::::::");
            studentRepository
                    .findStudentByFirstNameEqualsAndAgeEquals("jarin", 22)
                    .forEach(System.out::println);

            System.out.println("with jpql::::::");
            studentRepository
                    .findStudentByFirstNameEqualsAndAgeEqualsJpql("jarin", 22)
                    .forEach(System.out::println);

            System.out.println("with native query::::::");
            studentRepository
                    .findStudentsByFirstNameLikeOrLastNameNative("si%","anjum")
                    .forEach(System.out::println);

            System.out.println("without native query::::::");
            studentRepository
                    .findStudentsByFirstNameLikeOrLastName("si%","anjum")
                    .forEach(System.out::println);

        };
    }

}
