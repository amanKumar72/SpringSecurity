package com.aman.SpringSecurityEx.service;

import com.aman.SpringSecurityEx.models.Student;
import com.aman.SpringSecurityEx.models.StudentPrinciple;
import com.aman.SpringSecurityEx.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyStudentsDetailsService implements UserDetailsService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=studentRepo.findByName(username);
        if(student==null){
            System.out.println("student not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new StudentPrinciple(student);
    }
}
