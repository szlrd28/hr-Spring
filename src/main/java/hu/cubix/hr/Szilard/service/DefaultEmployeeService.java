package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;

public class DefaultEmployeeService implements  EmployeeService {

    @Override
    public int getPayRaisePercent(Employee employee) {
        return 5;
    }
}
