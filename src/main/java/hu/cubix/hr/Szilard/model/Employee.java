package hu.cubix.hr.Szilard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private String name;
    //	private String jobTitle;
    private int salary;
    private LocalDateTime dateOfStartWork;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Position position;

    public Employee() {
    }

    public Employee(Long employeeId, String name, int salary, LocalDateTime dateOfStartWork) {
        this.employeeId = employeeId;
        this.name = name;
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
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return Objects.equals(employeeId, other.employeeId);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}