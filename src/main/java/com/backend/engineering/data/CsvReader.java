package com.backend.engineering.data;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("employees.csv")) {
            List<Employee> employees = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .build()
                    .parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> readEmpData() throws FileNotFoundException {

        List<Employee> employees;

        FileReader reader = new FileReader("employees.csv") ;
            employees = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .build()
                    .parse();

        return employees;
    }
}
