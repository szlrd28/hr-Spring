package hu.cubix.hr.Szilard;

import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.service.SalaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	public HrApplication(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	private final SalaryService salaryService;
	@Override
	public void run(String... args) throws Exception {
		Employee employee1 = new Employee("John Doe", 1L, "Developer", 50000, LocalDateTime.now());
		Employee employee2 = new Employee("Jane Smith", 2L,"Designer", 60000, LocalDateTime.now());

		int modifiedSalary1 = salaryService.calculateNewSalary(employee1);
		int modifiedSalary2 = salaryService.calculateNewSalary(employee2);


		System.out.println("Modified Salary for " + employee1.getName() + ": $" + modifiedSalary1);
		System.out.println("Modified Salary for " + employee2.getName() + ": $" + modifiedSalary2);
	}
}
