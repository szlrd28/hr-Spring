package hu.cubix.hr.Szilard.dto;

import java.util.List;

public class CompanyDto {


    private int id;
    private int registrationNumber;
    private String name;
    private String address;
    private List<EmployeeDto> employees;

    public CompanyDto(){}
    public CompanyDto(int id, int registrationNumber, String name, String address, List<EmployeeDto> employees) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public CompanyDto(CompanyDto company) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}
