package hu.cubix.hr.Szilard.repository;


import hu.cubix.hr.Szilard.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    public List<Position> findByName(String name);
}
