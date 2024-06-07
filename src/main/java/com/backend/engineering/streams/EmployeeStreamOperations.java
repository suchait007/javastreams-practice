package com.backend.engineering.streams;


import com.backend.engineering.data.CsvReader;
import com.backend.engineering.data.Employee;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class EmployeeStreamOperations {

    public static void main(String[] args) throws FileNotFoundException {

        CsvReader csvReader = new CsvReader();
        List<Employee> employees = csvReader.readEmpData();

        // 1. Find all employees with a salary greater than 100,000
        List<Employee> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 100000)
                .collect(Collectors.toList());
        System.out.println("High Earners: " + highEarners.size());

        // 2. Count the number of employees in each department
        Map<String, Long> employeesPerDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Employees per Department: " + employeesPerDepartment);

        // 3. Find the average salary in the company
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average Salary: " + averageSalary);

        // 4. Get a list of all employees hired after 2015
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Employee> hiredAfter2015 = employees.stream()
                .filter(e -> LocalDate.parse(e.getHireDate(), formatter).isAfter(LocalDate.of(2015, 12, 31)))
                .collect(Collectors.toList());
        System.out.println("Hired After 2015: " + hiredAfter2015.size());

        // 5. Find the employee with the highest salary
        Employee highestEarner = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        System.out.println("Highest Earner: " + highestEarner);

        // 6. List all distinct positions in the company
        List<String> positions = employees.stream()
                .map(Employee::getPosition)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct Positions: " + positions);

        // 7. Count the number of employees managed by each manager
        Map<String, Long> employeesPerManager = employees.stream()
                .collect(Collectors.groupingBy(Employee::getManager, Collectors.counting()));
        System.out.println("Employees per Manager: " + employeesPerManager);

        // 8. Find the sum of all salaries in the company
        double totalSalaries = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Salaries: " + totalSalaries);

        // 9. List the first names of all employees
        List<String> firstNames = employees.stream()
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
        System.out.println("First Names: " + firstNames.size());

        // 10. Find the employee with the earliest hire date
        Employee earliestHired = employees.stream()
                .min(Comparator.comparing(e -> LocalDate.parse(e.getHireDate(), formatter)))
                .orElse(null);
        System.out.println("Earliest Hired: " + earliestHired);

        // 11. Group employees by department and list their names
        Map<String, List<String>> namesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getFirstName, Collectors.toList())));
        System.out.println("Names by Department: " + namesByDepartment);

        // 12. Get the average salary per department
        Map<String, Double> averageSalaryPerDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salary per Department: " + averageSalaryPerDepartment);

        // 13. Find the top 3 highest paid employees
        List<Employee> top3Earners = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 Earners: " + top3Earners);

        // 14. Get a list of emails of all employees
        List<String> emails = employees.stream()
                .map(Employee::getEmail)
                .collect(Collectors.toList());
        System.out.println("Emails: " + emails.size());

        // 15. Find all employees whose first name starts with 'J'
        List<Employee> firstNameStartsWithJ = employees.stream()
                .filter(e -> e.getFirstName().startsWith("J"))
                .collect(Collectors.toList());
        System.out.println("First Name starts with J: " + firstNameStartsWithJ.size());

        // 16. Get a map of employee names to their salaries
        Map<String, Double> nameToSalary = employees.stream()
                .collect(Collectors.toMap(e -> e.getFirstName() + " " + e.getLastName(), Employee::getSalary));
        System.out.println("Name to Salary: " + nameToSalary);

        // 17. Find the number of distinct managers
        long distinctManagers = employees.stream()
                .map(Employee::getManager)
                .distinct()
                .count();
        System.out.println("Distinct Managers: " + distinctManagers);

        // 18. Get a list of all phone numbers
        List<String> phoneNumbers = employees.stream()
                .map(Employee::getPhone)
                .collect(Collectors.toList());
        System.out.println("Phone Numbers: " + phoneNumbers.size());

        // 19. Find the employee with the shortest first name
        Employee shortestFirstName = employees.stream()
                .min(Comparator.comparingInt(e -> e.getFirstName().length()))
                .orElse(null);
        System.out.println("Shortest First Name: " + shortestFirstName);

        // 20. Group employees by their hire date year
        Map<Integer, List<Employee>> employeesByYear = employees.stream()
                .collect(Collectors.groupingBy(e -> LocalDate.parse(e.getHireDate(), formatter).getYear()));
        System.out.println("Employees by Year: " + employeesByYear);

        // 21. Find the sum of salaries of employees in the IT department
        double itSalaries = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("IT Salaries: " + itSalaries);

        // 22. Get the first and last names of employees in alphabetical order
        List<String> sortedNames = employees.stream()
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted Names: " + sortedNames);

        // 23. Count the number of employees with a salary less than 50,000
        long lowEarners = employees.stream()
                .filter(e -> e.getSalary() < 50000)
                .count();
        System.out.println("Low Earners: " + lowEarners);

        // 24. Find the most common first name
        String mostCommonFirstName = employees.stream()
                .collect(Collectors.groupingBy(Employee::getFirstName, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Most Common First Name: " + mostCommonFirstName);

        // 25. Get the employee IDs of employees managed by "John Doe"
        List<Integer> idsManagedByJohnDoe = employees.stream()
                .filter(e -> "John Doe".equals(e.getManager()))
                .map(Employee::getId)
                .collect(Collectors.toList());
        System.out.println("IDs Managed by John Doe: " + idsManagedByJohnDoe);

        // 26. Find the highest salary in each department
        Map<String, Optional<Employee>> highestSalaryInDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println("Highest Salary in Department: " + highestSalaryInDepartment);

        // 27. Calculate the total number of employees
        long totalEmployees = employees.stream().count();
        System.out.println("Total Employees: " + totalEmployees);

        // 28. Get the department with the highest average salary
        String departmentWithHighestAvgSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Department with Highest Avg Salary: " + departmentWithHighestAvgSalary);

        // 29. List all employees sorted by hire date
        List<Employee> sortedByHireDate = employees.stream()
                .sorted(Comparator.comparing(e -> LocalDate.parse(e.getHireDate(), formatter)))
                .collect(Collectors.toList());
        System.out.println("Sorted by Hire Date: " + sortedByHireDate.size());

        // 30. Get the top 5 highest paid employees' email addresses
        List<String> top5Emails = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(5)
                .map(Employee::getEmail)
                .collect(Collectors.toList());
        System.out.println("Top 5 Emails: " + top5Emails);

        // 31. Calculate the total salary of employees managed by "Jane Smith"
        double totalSalaryManagedByJane = employees.stream()
                .filter(e -> "Jane Smith".equals(e.getManager()))
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Salary Managed by Jane Smith: " + totalSalaryManagedByJane);

        // 32. Find the manager with the most employees
        String managerWithMostEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getManager, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Manager with Most Employees: " + managerWithMostEmployees);

        // 33. List the names of employees who have "Manager" in their position title
        List<String> managers = employees.stream()
                .filter(e -> e.getPosition().contains("Manager"))
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
        System.out.println("Managers: " + managers);

        // 34. Find the oldest and newest hire dates
        LocalDate oldestHireDate = employees.stream()
                .map(e -> LocalDate.parse(e.getHireDate(), formatter))
                .min(LocalDate::compareTo)
                .orElse(null);
        LocalDate newestHireDate = employees.stream()
                .map(e -> LocalDate.parse(e.getHireDate(), formatter))
                .max(LocalDate::compareTo)
                .orElse(null);
        System.out.println("Oldest Hire Date: " + oldestHireDate);
        System.out.println("Newest Hire Date: " + newestHireDate);

        // 35. Find all employees who have been with the company for more than 10 years
        List<Employee> moreThan10Years = employees.stream()
                .filter(e -> LocalDate.parse(e.getHireDate(), formatter).isBefore(LocalDate.now().minusYears(10)))
                .collect(Collectors.toList());
        System.out.println("More than 10 Years: " + moreThan10Years.size());

        // 36. Group employees by position and list their names
        Map<String, List<String>> namesByPosition = employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition,
                        Collectors.mapping(e -> e.getFirstName() + " " + e.getLastName(), Collectors.toList())));
        System.out.println("Names by Position: " + namesByPosition);

        // 37. Calculate the median salary
        List<Double> sortedSalaries = employees.stream()
                .map(Employee::getSalary)
                .sorted()
                .collect(Collectors.toList());
        double medianSalary;
        if (sortedSalaries.size() % 2 == 0) {
            medianSalary = (sortedSalaries.get(sortedSalaries.size() / 2 - 1) + sortedSalaries.get(sortedSalaries.size() / 2)) / 2;
        } else {
            medianSalary = sortedSalaries.get(sortedSalaries.size() / 2);
        }
        System.out.println("Median Salary: " + medianSalary);

        // 38. Find the average salary of employees hired in the last 5 years
        double avgSalaryLast5Years = employees.stream()
                .filter(e -> LocalDate.parse(e.getHireDate(), formatter).isAfter(LocalDate.now().minusYears(5)))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average Salary Last 5 Years: " + avgSalaryLast5Years);

        // 39. Find the number of employees in each position within each department
        Map<String, Map<String, Long>> employeesByDeptAndPos = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getPosition, Collectors.counting())));
        System.out.println("Employees by Dept and Pos: " + employeesByDeptAndPos);

        // 40. Get the list of employees whose salary is above the average salary
        double overallAverageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        List<Employee> aboveAverageSalary = employees.stream()
                .filter(e -> e.getSalary() > overallAverageSalary)
                .collect(Collectors.toList());
        System.out.println("Above Average Salary: " + aboveAverageSalary.size());

        // 41. List the names of employees hired on weekends
        List<String> hiredOnWeekends = employees.stream()
                .filter(e -> {
                    LocalDate hireDate = LocalDate.parse(e.getHireDate(), formatter);
                    return hireDate.getDayOfWeek() == DayOfWeek.SATURDAY || hireDate.getDayOfWeek() == DayOfWeek.SUNDAY;
                })
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
        System.out.println("Hired on Weekends: " + hiredOnWeekends);

        // 42. Find the average salary of employees with "Developer" in their position
        double avgDeveloperSalary = employees.stream()
                .filter(e -> e.getPosition().contains("Developer"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average Developer Salary: " + avgDeveloperSalary);

        // 43. Find the number of employees hired in each month of the year
        Map<Integer, Long> employeesHiredPerMonth = employees.stream()
                .collect(Collectors.groupingBy(e -> LocalDate.parse(e.getHireDate(), formatter).getMonthValue(), Collectors.counting()));
        System.out.println("Employees Hired per Month: " + employeesHiredPerMonth);

        // 44. Get the list of employees sorted by their last name and then first name
        List<Employee> sortedByLastNameThenFirstName = employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(Collectors.toList());
        System.out.println("Sorted by Last Name and First Name: " + sortedByLastNameThenFirstName.size());

        // 45. Find the department with the highest total salary
        String deptWithHighestTotalSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Department with Highest Total Salary: " + deptWithHighestTotalSalary);

        // 46. List the names of employees with phone numbers starting with "555"
        List<String> phoneStartsWith555 = employees.stream()
                .filter(e -> e.getPhone().startsWith("555"))
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
        System.out.println("Phone starts with 555: " + phoneStartsWith555);

        // 47. Calculate the average tenure of employees in the company
        double avgTenure = employees.stream()
                .mapToLong(e -> LocalDate.now().toEpochDay() - LocalDate.parse(e.getHireDate(), formatter).toEpochDay())
                .average()
                .orElse(0.0) / 365.0;
        System.out.println("Average Tenure: " + avgTenure);

        // 48. Find the department with the most employees who have "Engineer" in their position title
        String deptWithMostEngineers = employees.stream()
                .filter(e -> e.getPosition().contains("Engineer"))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Department with Most Engineers: " + deptWithMostEngineers);

        // 49. Get the list of managers who manage more than 5 employees
        List<String> managersWithMoreThan5Employees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getManager, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Managers with more than 5 employees: " + managersWithMoreThan5Employees);

        // 50. Find the number of employees whose last names have more than 5 letters
        long lastNameMoreThan5Letters = employees.stream()
                .filter(e -> e.getLastName().length() > 5)
                .count();
        System.out.println("Last Names more than 5 letters: " + lastNameMoreThan5Letters);

    }
}

