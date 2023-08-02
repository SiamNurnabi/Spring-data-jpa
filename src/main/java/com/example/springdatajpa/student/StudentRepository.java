package com.example.springdatajpa.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    /* these two are similar.
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByEmail(String email); this will generate
    "select s from Student s where s.email = ?1" this jpql
    */

    @Query("Select s from Student s Where s.firstName = ?1 And s.age= ?2 ")
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    List<Student> findStudentsByFirstNameLikeOrLastName(String firstName, String LastName);
}
