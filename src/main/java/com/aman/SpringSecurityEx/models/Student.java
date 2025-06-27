package com.aman.SpringSecurityEx.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(schema = "student")//to change the name of the table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  //primary key

    private String name; //to change the name of the column

    private String email;

    private int age;

    private String password;

}

