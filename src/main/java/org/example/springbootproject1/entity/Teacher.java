package org.example.springbootproject1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String phoneNumber;
    private String qualification;
    private Integer experienceYears;
    private String specialization;
    private LocalDate joiningDate;
}