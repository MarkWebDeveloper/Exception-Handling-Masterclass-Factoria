package dev.mark.exception_handling.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.mark.exception_handling.error_handling.ErrorResponse;

@RestController
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/getStudent/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/getStudentChecked/{id}")
    public Student getStudentChecked(@PathVariable("id") Long id) throws StudentNotFoundException {
        return studentService.getStudentChecked(id);
    }

    @GetMapping("/getStudentChecked2/{id}")
    public ResponseEntity<Student> getStudentReturnOtherStudent(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentChecked(id));
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(studentService.getStudent(1L));
        }
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @ExceptionHandler(value = StudentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}