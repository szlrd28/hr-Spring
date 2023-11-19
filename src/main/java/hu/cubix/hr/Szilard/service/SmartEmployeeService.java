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

//		if(yearsWorked > smartConfig.getLimit3())
//			return smartConfig.getPercent3();
//
//		if(yearsWorked > smartConfig.getLimit2())
//			return smartConfig.getPercent2();
//
//		if(yearsWorked > smartConfig.getLimit1())
//			return smartConfig.getPercent1();
//
//		return 0;


        TreeMap<Double, Integer> limits = smartConfig.getLimits();
//opcionális 1. megoldás
//		Integer maxLimit = null;
//
//		for(var entry: limits.entrySet()) {
//			if(yearsWorked > entry.getKey())
//				maxLimit = entry.getValue();
//			else
//				break;
//		}
//
//		return maxLimit == null ? 0 : maxLimit;

//opcionális 2. megoldás
//		Optional<Double> optionalMax = limits.keySet().stream()
//		.filter(k -> yearsWorked > k)
//		.max(Double::compare);
//
//		return optionalMax.isEmpty() ? 0 : limits.get(optionalMax.get());

        //opcionális 3. megoldás
        Map.Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
        return floorEntry == null ? 0 : floorEntry.getValue();

    }
}