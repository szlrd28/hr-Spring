package hu.cubix.hr.Szilard.repository;

import hu.cubix.hr.Szilard.model.PositionDetailsByCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> {

    List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);

}
