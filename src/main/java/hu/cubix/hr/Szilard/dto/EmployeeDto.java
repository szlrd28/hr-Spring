package hu.cubix.hr.Szilard.dto;

import java.time.LocalDateTime;

public class EmployeeDto {

    private Long id;
    private String name;
    private String job;
    private int salary;
    private LocalDateTime startedWorkingAt;

    public EmployeeDto(){

    }

    public EmployeeDto(Long id, String name, String job, int salary, LocalDateTime startedWorkingAt) {
        super();
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.startedWorkingAt = startedWorkingAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartedWorkingAt() {
        return startedWorkingAt;
    }

    public void setStartedWorkingAt(LocalDateTime startedWorkingAt) {
        this.startedWorkingAt = startedWorkingAt;
    }
}
