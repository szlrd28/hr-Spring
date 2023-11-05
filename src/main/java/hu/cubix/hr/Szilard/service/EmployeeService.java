package hu.cubix.hr.Szilard.service;

import hu.cubix.hr.Szilard.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {


    public Employee save(Employee employee);

    public Employee update(Employee employee);

    public List<Employee> findAll();

    public Optional<Employee> findById(long id);

    public void delete(long id);
    int getPayRaisePercent(Employee employee);

}
