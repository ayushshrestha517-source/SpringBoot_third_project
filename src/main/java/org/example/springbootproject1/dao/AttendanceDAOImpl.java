package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.AttendanceRequestDTO;
import org.example.springbootproject1.dto.response.AttendanceReportResponseDTO;
import org.example.springbootproject1.dto.response.AttendanceResponseDTO;
import org.example.springbootproject1.entity.Attendance;
import org.example.springbootproject1.entity.AttendanceDetail;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.entity.Student;
import org.example.springbootproject1.entity.Subject;
import org.example.springbootproject1.entity.Teacher;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.AttendanceMapper;
import org.example.springbootproject1.repository.AttendanceRepository;
import org.example.springbootproject1.repository.FacultyRepository;
import org.example.springbootproject1.repository.SemesterRepository;
import org.example.springbootproject1.repository.StudentRepository;
import org.example.springbootproject1.repository.SubjectRepository;
import org.example.springbootproject1.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceDAOImpl implements AttendanceDAO {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final TeacherRepository teacherRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    @Override
    @Transactional
    public AttendanceResponseDTO saveOrUpdateAttendance(AttendanceRequestDTO dto) {

        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() ->new ResourceNotFoundException("Teacher not found"));

        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        Subject subject = subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));


        // Check semester belongs to faculty
        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }
        // Check subject belongs to semester
        if (!subject.getSemester().getId().equals(semester.getId())) {
            throw new ResourceNotFoundException("Subject does not belong to selected semester");
        }

        // Find existing attendance
        Attendance attendance = attendanceRepository
                .findByTeacherIdAndFacultyIdAndSemesterIdAndSubjectIdAndAttendanceDate(
                        dto.teacherId(),
                        dto.facultyId(),
                        dto.semesterId(),
                        dto.subjectId(),
                        dto.attendanceDate()
                )
                .orElse(null);





        // CREATE
        if (attendance == null) {
            attendance = new Attendance();
            attendance.setAttendanceDate(dto.attendanceDate());
            attendance.setTeacher(teacher);
            attendance.setFaculty(faculty);
            attendance.setSemester(semester);
            attendance.setSubject(subject);


            // Add students
            for (var detailDTO : dto.attendanceDetails()) {

                Student student = studentRepository.findById(detailDTO.studentId()).orElseThrow(() -> new ResourceNotFoundException("Student not found"));

                AttendanceDetail detail = new AttendanceDetail();

                detail.setAttendance(attendance);
                detail.setStudent(student);
                detail.setStatus(detailDTO.status());

                attendance.getAttendanceDetails().add(detail);
            }
        }


        // UPDATE
        else {

            for (var detailDTO : dto.attendanceDetails()) {

                AttendanceDetail detail = attendance.getAttendanceDetails()
                        .stream()
                        .filter(d ->
                                d.getStudent().getId()
                                        .equals(detailDTO.studentId()))
                        .findFirst()
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance record for student not found"));

                // Only update status
                detail.setStatus(detailDTO.status());
            }
        }

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponseDTO(savedAttendance);
    }

    @Override
    public AttendanceReportResponseDTO getAttendanceReport(Long facultyId, Long semesterId, Long studentId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        Semester semester = semesterRepository.findById(semesterId).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }
            if (!studentRepository.existsById(studentId)) {
                throw new ResourceNotFoundException("Student with id " + studentId + " not found");
            }

            return attendanceRepository.getAttendanceReport(studentId);
        }



}