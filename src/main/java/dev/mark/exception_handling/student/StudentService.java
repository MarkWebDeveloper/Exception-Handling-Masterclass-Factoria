package dev.mark.exception_handling.student;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRespository;

    public Student getStudent(Long id){

        return studentRespository.findById(id).orElseThrow(() -> new NoSuchElementException("NO STUDENT PRESENT WITH ID = " + id));
    }

    public Student getStudentChecked(Long id) throws StudentNotFoundException {

        return studentRespository.findById(id).orElseThrow(() -> new StudentNotFoundException("NO STUDENT PRESENT WITH ID = " + id));
    }

    public String addStudent(Student student)
    {
        Student existingStudent = studentRespository.findById(student.getId()).orElse(null);
        
        if (existingStudent == null) {
            studentRespository.save(student);
            return "Student added successfully";
        } else
            throw new StudentAlreadyExistsException("Student already exists!!");
    }

    public String updateStudent(Student student){
        Student existingStudent = studentRespository.findById(student.getId()).orElse(null);
        if (existingStudent == null)
            throw new NoSuchStudentExistsException("No Such Student exists!!");
        else {
            existingStudent.setName(student.getName());
            existingStudent.setAddress(
                student.getAddress());
            studentRespository.save(existingStudent);
            return "Record updated Successfully";
        }
    }
}