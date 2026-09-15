package org.example.springbootproject1.dto.response;

public record TeacherAssignedClassResponseDTO(
        Long id,

        Long facultyId,
        String facultyName,

        Long semesterId,
        String semesterName,

        Long subjectId,
        String subjectName,
        String courseCode
) {
}
