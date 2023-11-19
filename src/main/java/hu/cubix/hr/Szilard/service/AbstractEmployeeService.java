package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.model.Position;
import hu.cubix.hr.Szilard.repository.EmployeeRepository;
import hu.cubix.hr.Szilard.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractEmployeeService implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Employee save(Employee employee) {
        processPosition(employee);
        return employeeRepository.save(employee);
    }

    private void processPosition(Employee employee) {
        String positionName = employee.getPosition().getName();
        if(positionName != null) {
            List<Position> positions = positionRepository.findByName(positionName);
            Position position = null;
            if(positions.isEmpty()) {
                position = positionRepository.save(new Position(positionName, null));
            } else {
                position = positions.get(0);
            }
            employee.setPosition(position);
        }
    }

    @Override
    public Employee update(Employee employee) {
        if(!employeeRepository.existsById(employee.getEmployeeId()))
            return null;
        processPosition(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
