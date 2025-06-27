package com.aman.SpringSecurityEx.controller;

import com.aman.SpringSecurityEx.models.Student;
import com.aman.SpringSecurityEx.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/greet")
    public String greet(HttpServletRequest http){
        return studentService.greet()+http.getSession().getId();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/register")
    public String addStudent(@RequestBody Student s){
        Student std=studentService.addStudent(s);
        if(std!=null){
            return "added";
        }else {
            return "not added";
        }
    }


    @PostMapping("/login")
    public String login(@RequestBody Student s){
        return studentService.verify(s);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }


}
