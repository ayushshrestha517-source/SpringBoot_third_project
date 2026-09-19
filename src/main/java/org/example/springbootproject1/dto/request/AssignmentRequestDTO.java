package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AssignmentRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
        String description,

        String imageUrl,

        @NotNull(message = "Due date is required")
        @Future(message = "Due date must be in the future")
        LocalDate dueDate,

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotNull(message = "Faculty ID is required")
        Long facultyId,

        @NotNull(message = "Semester ID is required")
        Long semesterId,

        @NotNull(message = "Subject ID is required")
        Long subjectId

) {
}
