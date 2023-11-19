package hu.cubix.hr.Szilard.service;


import hu.cubix.hr.Szilard.model.Company;
import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.repository.CompanyRepository;
import hu.cubix.hr.Szilard.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Company company) {
        if(!companyRepository.existsById(company.getId()))
            return null;
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    public void delete(long id) {

        //companyRepository.deleteById(id); --> csak akkor megy, ha nincs employee-ja
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()) {
            company.get().getEmployees().forEach(e -> {
                e.setCompany(null);
                employeeService.save(e);
            });
        }
        companyRepository.deleteById(id);
    }


    public Company addEmployee(long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeService.save(employee);
        return company;
    }

    public Company deleteEmployee(long id, long employeeId) {
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeService.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployees().remove(employee);
        employeeService.save(employee);
        return company;
    }

    public Company replaceEmployees(long id, List<Employee> employees) {
        Company company = companyRepository.findById(id).get();
        company.getEmployees().forEach(e -> {
            e.setCompany(null);
            employeeService.save(e);
        });
        company.getEmployees().clear();
        employees.forEach(e -> {
            company.addEmployee(e);
            employeeService.save(e);
        });
        return company;
    }

}
