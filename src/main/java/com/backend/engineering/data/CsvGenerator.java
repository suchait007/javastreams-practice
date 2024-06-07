package com.backend.engineering.data;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CsvGenerator {

    private static final String[] DEPARTMENTS = {"HR", "Engineering", "Sales", "Marketing"};
    private static final String[] POSITIONS = {"Manager", "Developer", "Salesperson", "Marketer"};
    private static final String[] MANAGERS = {"John Doe", "Jane Smith", "Jim Brown", "Judy White"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("employees.csv"))) {
            // Write header
            String[] header = {"ID", "First Name", "Last Name", "Email", "Phone", "Department", "Position", "Salary", "Hire Date", "Manager"};
            writer.writeNext(header);

            // Write 1000 records
            for (int i = 1; i <= 1000; i++) {
                String[] record = generateRecord(i);
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] generateRecord(int id) {
        String firstName = "FirstName" + id;
        String lastName = "LastName" + id;
        String email = "email" + id + "@example.com";
        String phone = "555-1234" + id;
        String department = DEPARTMENTS[RANDOM.nextInt(DEPARTMENTS.length)];
        String position = POSITIONS[RANDOM.nextInt(POSITIONS.length)];
        double salary = 50000 + (RANDOM.nextDouble() * 50000);
        String hireDate = "2023-01-" + (RANDOM.nextInt(28) + 1);
        String manager = MANAGERS[RANDOM.nextInt(MANAGERS.length)];

        return new String[]{String.valueOf(id), firstName, lastName, email, phone, department, position, String.valueOf(salary), hireDate, manager};
    }
}
