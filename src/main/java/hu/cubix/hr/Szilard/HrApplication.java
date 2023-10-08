package hu.cubix.hr.Szilard;

import hu.cubix.hr.Szilard.config.SmartEmployeeProperties;
import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDateTime;

@SpringBootApplication
//@EnableConfigurationProperties(SmartEmployeeProperties.class)
public class HrApplication implements CommandLineRunner {

	private final SalaryService salaryService;

	@Autowired
	public HrApplication(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee emp1 = new Employee(1L, "János", "fejlesztő", 300000, LocalDateTime.now().minusYears(11));
		Employee emp2 = new Employee(2L, "Anna", "tesztelő", 250000, LocalDateTime.now().minusYears(6));
		Employee emp3 = new Employee(3L, "Péter", "mérnök", 350000, LocalDateTime.now().minusYears(3));
		Employee emp4 = new Employee(4L, "Zsófia", "designer", 280000, LocalDateTime.now().minusYears(1));

		salaryService.setNewSalary(emp1);
		salaryService.setNewSalary(emp2);
		salaryService.setNewSalary(emp3);
		salaryService.setNewSalary(emp4);


		System.out.println(  emp1.getName() + " nevű alkalmazott új fizetése:  "  + emp1.getSalary() + " Ft.");
		System.out.println( emp2.getName() +" nevű alkalmazott új fizetése: " + emp2.getSalary() + " Ft.");
		System.out.println( emp3.getName() +" nevű alkalmazott új fizetése: " + emp3.getSalary() + " Ft.");
		System.out.println( emp4.getName() +" nevű alkalmazott új fizetése: " + emp4.getSalary() + " Ft.");
	}



	}


