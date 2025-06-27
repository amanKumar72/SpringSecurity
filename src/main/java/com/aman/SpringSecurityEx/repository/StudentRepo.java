package com.aman.SpringSecurityEx.repository;

import com.aman.SpringSecurityEx.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    public Student findByName(String name);
}
