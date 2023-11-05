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

    @NotNull(message = "A név nem lehet null")
    @NotEmpty(message = "A név nem lehet üres")
    private String name;


    @NotNull(message = "A munkakör megnevezése nem lehet null")
    @NotEmpty(message = "A munkakör megnevezése nem lehet üres")
    private String title;



    @NotNull(message = "A fizetés nem lehet null")
    @Positive(message = "A fizetésnek pozitívnak kell lennie")
    private int salary;

    @NotNull(message = "A belépési dátum nem lehet null")
    @Past(message = "A belépési dátumnak a múltban kell lennie")
    private LocalDateTime entryDate;

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

}
