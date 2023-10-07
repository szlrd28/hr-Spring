package hu.cubix.hr.Szilard.model;

import javax.lang.model.element.Name;
import java.time.LocalDateTime;

public class Employee {

    private Long id;



    private String name ;
    private String job;
    private Integer salary;
    private LocalDateTime startDate;

    public Employee(String name,Long id, String job, Integer salary, LocalDateTime startDate) {
        this.id = id;
        this.job = job;
        this.salary = salary;
        this.startDate = startDate;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
