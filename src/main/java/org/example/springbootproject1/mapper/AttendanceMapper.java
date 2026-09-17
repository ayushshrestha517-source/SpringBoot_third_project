package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.AttendanceRequestDTO;
import org.example.springbootproject1.dto.request.AttendanceDetailRequestDTO;
import org.example.springbootproject1.dto.response.AttendanceResponseDTO;
import org.example.springbootproject1.dto.response.AttendanceDetailResponseDTO;
import org.example.springbootproject1.entity.Attendance;
import org.example.springbootproject1.entity.AttendanceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(source = "teacherId", target = "teacher.id")
    @Mapping(source = "facultyId", target = "faculty.id")
    @Mapping(source = "semesterId", target = "semester.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Attendance toEntity(AttendanceRequestDTO dto);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "subject.id", target = "subjectId")
    AttendanceResponseDTO toResponseDTO(Attendance attendance);


    @Mapping(source = "studentId", target = "student.id")
    AttendanceDetail toEntity(AttendanceDetailRequestDTO dto);

    @Mapping(source = "student.id", target = "studentId")
    AttendanceDetailResponseDTO toResponseDTO(AttendanceDetail attendanceDetail);
}