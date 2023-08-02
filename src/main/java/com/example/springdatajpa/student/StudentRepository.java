package com.example.springdatajpa.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query("Select s from Student s Where s.firstName = ?1 And s.age= ?2 ")
    List<Student> findStudentByFirstNameEqualsAndAgeEqualsJpql(String firstName, Integer age);

    List<Student> findStudentsByFirstNameLikeOrLastName(String firstName, String LastName);

    @Query(value = "SELECT * FROM Student WHERE \n" +
            "    firstName LIKE ?1 OR lastName = ?2",
            nativeQuery = true)
    List<Student> findStudentsByFirstNameLikeOrLastNameNative(String firstName, String LastName);

    @Query(value = "SELECT * FROM Student WHERE \n" +
            "    firstName LIKE :firstName OR lastName = :lastName",
            nativeQuery = true)
    List<Student> findStudentsByFirstNameLikeOrLastNameNative_NamedParameter(
            @Param("firstName") String firstName,
            @Param("lastName") String LastName);

    //@Query expects an entity or entities to be returned from the database.
    //@Modifying tells spring data that query doesn't need to map anything from the database into entity.
    // Instead, we are modifying some data in our table. To use update, we need to use @Modifying also.
    @Transactional  // Need to delete and update
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = ?1")
    int deleteStudentById(Long id);
}
