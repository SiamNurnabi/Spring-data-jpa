package com.example.springdatajpa.student;

import jakarta.persistence.*;

@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "card_number_unique",
                        columnNames = {"card_number"}
                )
        }
)
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence_db",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "card_number",
            nullable = false)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student studentId;


    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Student getStudentId() {
        return studentId;
    }

    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student studentId) {
        this.cardNumber = cardNumber;
        this.studentId = studentId;
    }
}
