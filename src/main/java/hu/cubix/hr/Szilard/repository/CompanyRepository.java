package hu.cubix.hr.Szilard.repository;

import hu.cubix.hr.Szilard.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
