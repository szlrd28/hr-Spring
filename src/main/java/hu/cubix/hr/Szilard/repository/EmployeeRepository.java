package hu.cubix.hr.Szilard.repository;

import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(Integer minSalary);

    List<Employee> findByJobTitle(String jobTitle);
    List<Employee> findByNameStartingWithIgnoreCase(String namePrefix);
    List<Employee> findByDateOfStartWorkBetween(LocalDateTime start, LocalDateTime end);
}
