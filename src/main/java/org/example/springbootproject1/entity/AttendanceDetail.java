package org.example.springbootproject1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.springbootproject1.util.StudentAttendanceStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "attendance_details", uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_attendance_student",
                        columnNames = {"attendance_id", "student_id"}
                )
        }
)
public class AttendanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attendance_id", nullable = false)
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentAttendanceStatus status;
}
