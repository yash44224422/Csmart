package org.yashrajguru.csmart.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yashrajguru.csmart.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,"YASH",60),
            new Student(1,"YASH2",60)
    ));
    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;

    }

    @PostMapping("/students")

    public void addStudent(@RequestBody Student student){
        students.add(student);
        // return student;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");// type casting object to csrf token

    }

}
