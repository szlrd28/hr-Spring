package hu.cubix.hr.Szilard.service;



import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SmartEmployeeService implements EmployeeService {

    @Value("${smartemployee.years.limits}")
    private List<Double> yearLimits;

    @Value("${smartemployee.raise.percents}")
    private List<Integer> raisePercents;

    @Override
    public int getPayRaisePercent(Employee employee) {
        long yearsWorked = ChronoUnit.YEARS.between(employee.getStartedWorkingAt(), LocalDateTime.now());

        for (int i = 0; i < yearLimits.size(); i++) {
            if (yearsWorked >= yearLimits.get(i)) {
                return raisePercents.get(i);
            }
        }
        return 0;
    }
}