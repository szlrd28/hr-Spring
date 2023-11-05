package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private EmployeeService employeeService;

    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setNewSalary(Employee employee) {
        int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
        employee.setSalary(newSalary);
    }
}