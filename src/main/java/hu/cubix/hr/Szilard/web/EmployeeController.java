package hu.cubix.hr.Szilard.web;


import ch.qos.logback.core.model.Model;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private Map<Long, EmployeeDto> employees = new HashMap<>();

    {

        employees.put(2L,new EmployeeDto(2L, "József", "Kisegitő", 350000, LocalDateTime.now().minusYears(12)));
        employees.put(3L,new EmployeeDto(3L, "András", "Villanyos", 550000, LocalDateTime.now().minusYears(3)));
        employees.put(4L,new EmployeeDto(4L, "Irénke", "Takaritó", 750000, LocalDateTime.now().minusYears(7)));
        employees.put(5L,new EmployeeDto(5L, "Margit", "Könyvelő", 950000, LocalDateTime.now().minusYears(6)));
    }
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return  new ArrayList<>(employees.values());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        EmployeeDto employeeDto = employees.get(id);
        if(employeeDto != null)
            return ResponseEntity.ok(employeeDto);
        else
            return ResponseEntity.notFound().build();

    }
    @PostMapping
    public EmployeeDto createEmployee (@RequestBody EmployeeDto employeeDto) {
        employees.put(employeeDto.getId(), employeeDto);
        return employeeDto;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<EmployeeDto> modifyAirport(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        if(!employees.containsKey(id)){
            return  ResponseEntity.notFound().build();
        }
        employeeDto.setId(id);
        employees.put(id, employeeDto);
        return  ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping ("/{id}")
    public void  deleteAirport(@PathVariable long id){
        employees.remove(id);

}

    @GetMapping (params = "minSalary")
        public List<EmployeeDto> listEmployeesWithSalaryGreaterThan(@RequestParam int minSalary ){
        return employees.values().stream().filter(e -> e.getSalary() > minSalary).toList();
    }


    }
