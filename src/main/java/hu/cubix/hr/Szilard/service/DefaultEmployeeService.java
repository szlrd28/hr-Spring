package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.cubix.hr.Szilard.config.HrConfigProperties;


@Service
public class DefaultEmployeeService extends AbstractEmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.getSalary().getDef().getPercent();
    }

}