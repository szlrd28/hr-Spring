package hu.cubix.hr.Szilard.web;


import ch.qos.logback.core.model.Model;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.mapper.EmployeeMapper;
import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.repository.EmployeeRepository;
import hu.cubix.hr.Szilard.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private Map<Long, EmployeeDto> employees = new HashMap<>();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeDto> findAll(Optional<Integer> minSalary) {
        List<Employee> employees = null;
        if (minSalary.isPresent()) {
            employees = employeeRepository.findBySalaryGreaterThan(minSalary.get());
        } else {
            employees = employeeService.findAll();
        }
        return employeeMapper.employeesToDtos(employees);
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable long id) {
        Employee employee = findByIdOrThrow(id);
        return employeeMapper.employeeToDto(employee);
    }
    private Employee findByIdOrThrow(long id) {
        return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public EmployeeDto create(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable long id,@Valid @RequestBody EmployeeDto employeeDto) {
        employeeDto.setId(id);
        Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        employees.remove(id);
    }

    @PostMapping("/payRaise")
    public int getPayRaisePercent(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }

}
