package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private final EmployeeService employeeService;

    @Autowired
    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setNewSalary(Employee employee) {
        int raisePercentage = employeeService.getPayRaisePercent(employee);
        int currentSalary = employee.getSalary();
        int newSalary = currentSalary + (currentSalary * raisePercentage / 100);
        employee.setSalary(newSalary);
    }
}