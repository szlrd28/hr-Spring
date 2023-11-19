package hu.cubix.hr.Szilard.web;


import ch.qos.logback.core.model.Model;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.mapper.EmployeeMapper;
import hu.cubix.hr.Szilard.model.Employee;
import hu.cubix.hr.Szilard.repository.EmployeeRepository;
import hu.cubix.hr.Szilard.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    //minSalary paraméter kezelése, 1. megoldás
//	@GetMapping
//	public List<EmployeeDto> findAll() {
//		return new ArrayList<>(employees.values());
//	}
//
//	@GetMapping(params = "minSalary")
//	public List<EmployeeDto> findBySalary(@RequestParam int minSalary){
//		return employees.values().stream().filter(e -> e.getSalary() > minSalary).toList();
//	}

    //minSalary paraméter kezelése, 2. megoldás
    @GetMapping
    public List<EmployeeDto> findAll(Optional<Integer> minSalary, @SortDefault("employeeId") Pageable pageable /*page, size, sort*/) {
        List<Employee> employees = null;
        if (minSalary.isPresent()) {
            Page<Employee> page = employeeRepository.findBySalaryGreaterThan(minSalary.get(), pageable);
            System.out.println(page.getTotalElements());
            System.out.println(page.isFirst());
            System.out.println(page.isLast());
            System.out.println(page.getNumberOfElements());
            employees = page.getContent();
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
    public ResponseEntity<EmployeeDto> update(@PathVariable long id, @Valid @RequestBody EmployeeDto employeeDto) {
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
        employeeService.delete(id);
    }

    @PostMapping("/payRaise")
    public int getPayRaisePercent(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }

}
