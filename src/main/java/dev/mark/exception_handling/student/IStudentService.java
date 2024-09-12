package dev.mark.exception_handling.student;

public interface IStudentService {

    Student getStudent(Long id);

    Student getStudentChecked(Long id) throws StudentNotFoundException;

    String addStudent(Student student);

    String updateStudent(Student student);
}