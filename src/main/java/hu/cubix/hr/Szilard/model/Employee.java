package hu.cubix.hr.Szilard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long employeeId;
    private String name;
    private String jobTitle;
    private int salary;
    private LocalDateTime dateOfStartWork;



    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    public Employee() {
    }

    public Employee(Long employeeId, String name, String jobTitle, int salary, LocalDateTime dateOfStartWork) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfStartWork = dateOfStartWork;
    }

    public Employee(int salary, LocalDateTime dateOfStartWork) {
        this.salary = salary;
        this.dateOfStartWork = dateOfStartWork;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getDateOfStartWork() {
        return dateOfStartWork;
    }

    public void setDateOfStartWork(LocalDateTime dateOfStartWork) {
        this.dateOfStartWork = dateOfStartWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}