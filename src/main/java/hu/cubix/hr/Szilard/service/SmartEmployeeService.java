package hu.cubix.hr.Szilard.service;



import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hu.cubix.hr.Szilard.config.HrConfigProperties;
import hu.cubix.hr.Szilard.config.HrConfigProperties.Smart;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {

        double yearsWorked = ChronoUnit.DAYS.between(employee.getDateOfStartWork(), LocalDateTime.now()) / 365.0;

        Smart smartConfig = config.getSalary().getSmart();

        TreeMap<Double, Integer> limits = smartConfig.getLimits();

        Map.Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
        return floorEntry == null ? 0 : floorEntry.getValue();

    }
}