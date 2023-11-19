package hu.cubix.hr.Szilard;

import hu.cubix.hr.Szilard.config.HrConfigProperties;
import hu.cubix.hr.Szilard.config.HrConfigProperties.Smart;

import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.service.InitDbService;
import hu.cubix.hr.Szilard.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;

@SpringBootApplication
//@RestController
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	InitDbService initDbService;

	@Autowired
	HrConfigProperties config;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Smart smartConfig = config.getSalary().getSmart();
		for (Double limit :
				smartConfig.getLimits().keySet()
			/*Arrays.asList(smartConfig.getLimit1(), smartConfig.getLimit2(), smartConfig.getLimit3())*/) {

			int origSalary = 100;
			LocalDateTime limitDay = LocalDateTime.now().minusDays((long)(limit*365));
			Employee e1 = new Employee(1L, "Nagy Péter", origSalary, limitDay.plusDays(1));
			Employee e2 = new Employee(2L, "Kis Gábor", origSalary, limitDay.minusDays(1));

			salaryService.setNewSalary(e1);
			salaryService.setNewSalary(e2);

			System.out.format("1 nappal a %.2f éves határ előtt az új fizetés %d%n", limit, e1.getSalary());
			System.out.format("1 nappal a %.2f éves határ után az új fizetés %d%n", limit, e2.getSalary());
		}

		initDbService.initDb();
	}



	}


