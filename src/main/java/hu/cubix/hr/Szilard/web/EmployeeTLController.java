package hu.cubix.hr.Szilard.web;



import ch.qos.logback.core.model.Model;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;


@Controller
public class EmployeeTLController {

    private List<Employee> allEmployees = new ArrayList<>();

    {
        allEmployees.add(new Employee(1L, "Kis Gábor", 100000, LocalDateTime.of(2012, 1, 1, 8, 0, 0)));
    }

    @GetMapping("/employees")
    public String listEmployees(Map<String, Object> model) {
        model.put("employees", allEmployees);
        model.put("newEmployee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(Employee employee) {
        allEmployees.add(employee);
        return "redirect:employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable long id) {
        allEmployees.removeIf(emp -> emp.getEmployeeId() == id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String editEmployee(@PathVariable long id, Map<String, Object> model) {
        model.put("employee", allEmployees.stream().filter(e -> e.getEmployeeId() == id).findFirst().get());
        return "editEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee employee) {
        for(int i=0; i< allEmployees.size(); i++) {
            if(allEmployees.get(i).getEmployeeId() == employee.getEmployeeId()) {
                allEmployees.set(i, employee);
                break;
            }
        }
        return "redirect:employees";
    }


}






