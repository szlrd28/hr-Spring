package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    private final EmployeeService employeeService;

    @Autowired
    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Bean
    public int calculateNewSalary(Employee employee) {
        int payRaisePercent = employeeService.getPayRaisePercent(employee);
        int currentSalary = employee.getSalary();
        int raiseAmount = (currentSalary * payRaisePercent) / 100;
        return currentSalary + raiseAmount;
    }

}
