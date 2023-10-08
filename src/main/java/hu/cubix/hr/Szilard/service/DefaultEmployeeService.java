package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultEmployeeService implements EmployeeService {

    @Override
    public int getPayRaisePercent(Employee employee) {

        return 5;
    }
}