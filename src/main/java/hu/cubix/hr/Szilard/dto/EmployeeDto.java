package hu.cubix.hr.Szilard.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDto {


    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String title;
    @Positive
    private int salary;
    @Past
    private LocalDateTime entryDate;

    private CompanyDto company;

    public EmployeeDto() {

    }

    public EmployeeDto(long id, String name, String title, int salary, LocalDateTime entryDate) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.salary = salary;
        this.entryDate = entryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", title=" + title + ", salary=" + salary + ", entryDate="
                + entryDate + "]";
    }
    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }
}
