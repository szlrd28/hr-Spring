package hu.cubix.hr.Szilard.repository;

import hu.cubix.hr.Szilard.model.AverageSalaryByPosition;
import hu.cubix.hr.Szilard.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
    public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);

    @Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
    public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

    @Query("SELECT e.position.name as position, avg(e.salary) as averageSalary "
            + "FROM Company c "
            + "INNER JOIN c.employees e "
            + "WHERE c.id = :companyId "
            + "GROUP BY e.position.name "
            + "ORDER BY avg(e.salary) desc")
    public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);

}
