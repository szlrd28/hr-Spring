package hu.cubix.hr.Szilard.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Company {


    @Id
    @GeneratedValue
    private Long id;
    private int registrationNumber;
    private String name;
    private String address;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @ManyToOne
    private CompanyType companyType;

    public Company() {

    }

    public Company(Long id, int registrationNumber, String name, String adress, List<Employee> employees) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = adress;
        this.employees = employees;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Employee> getEmployees() {
        if(this.employees == null)
            this.employees = new ArrayList<>();
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee emp) {
        emp.setCompany(this);
        getEmployees().add(emp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        return Objects.equals(id, other.id);
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }
}
