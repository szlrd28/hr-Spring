package hu.cubix.hr.Szilard.model;

import java.time.LocalDateTime;

public class Employee {

    private Long id;



    private String name;
    private String job;
    private int salary;
    private LocalDateTime startedWorkingAt;


    public Employee(Long id,String name , String job, int salary, LocalDateTime startedWorkingAt) {

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