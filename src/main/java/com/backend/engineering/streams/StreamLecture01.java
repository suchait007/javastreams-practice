package com.backend.engineering.streams;

import com.backend.engineering.data.CsvReader;
import com.backend.engineering.data.Employee;

import java.io.FileNotFoundException;
import java.util.List;

public class StreamLecture01 {

    public static void main(String[] args) throws FileNotFoundException {

        CsvReader csvReader = new CsvReader();
        List<Employee> employeeList = csvReader.readEmpData();

        System.out.println("Total employee list: " + employeeList.size());

        employeeList.stream()
                .filter(employee -> employee.getDepartment().equals("Engineering"))
                .forEach(employee -> {
                    System.out.println("Employee: " + employee.getFirstName() + " " + employee.getLastName());
                });

    }
}
