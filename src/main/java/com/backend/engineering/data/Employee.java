package com.backend.engineering.data;


import lombok.Data;

@Data
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String position;
    private double salary;
    private String hireDate;
    private String manager;
}
