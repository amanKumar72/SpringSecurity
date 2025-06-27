package com.aman.SpringSecurityEx.service;

import com.aman.SpringSecurityEx.models.Student;
import com.aman.SpringSecurityEx.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    @Autowired
    JwtService jwtService;

    @Autowired
    private StudentRepo studentRepo;


    public String greet(){
        return "welcome";
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student addStudent(Student s){
        s.setPassword(encoder.encode(s.getPassword()));
        return studentRepo.save(s);
    }

    public String deleteStudent(Integer id){
        studentRepo.deleteById(id);
        return "student with id "+id+"is deleted";
    }


    public String verify(Student s) {
        Student s1=studentRepo.findByName(s.getName());
        if(s1!=null) {
            boolean res = encoder.matches(s.getPassword(), s1.getPassword());
            if(res){
                return jwtService.generateToken(s.getName());
            }else {
                return "wrong password";
            }
        }else {
            return "no user found";
        }
    }
}
