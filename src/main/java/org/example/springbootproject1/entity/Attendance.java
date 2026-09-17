package org.example.springbootproject1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "attendances",
        uniqueConstraints = {@UniqueConstraint(
                name = "uk_attendance",
                columnNames = { "teacher_id",
                        "faculty_id",
                        "semester_id",
                        "subject_id",
                        "attendance_date"
                }
        )
        }
)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id",nullable = false )
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semester_id",nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "subject_id",nullable = false)
    private Subject subject;

    @Builder.Default
    @OneToMany(mappedBy = "attendance",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AttendanceDetail> attendanceDetails = new ArrayList<>();

}
