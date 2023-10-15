package hu.cubix.hr.Szilard.web;



import ch.qos.logback.core.model.Model;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;


@Controller
public class EmployeeTLController {

    private List<EmployeeDto> allEmployees = new ArrayList<>();
    {
        System.out.println("Get employees");
    allEmployees.add(new EmployeeDto(1L, "József", "Mérnök", 400000, LocalDateTime.of(2012,1,1,8,0,0)));

}

    @GetMapping("/")
    public String home (Map<String, Object> model){
        model.put("employees", allEmployees);
        System.out.println("Get index");
        return "index";
    }

    @GetMapping("/employees")
    public String listEmployees(Map<String, Object> model) {
        model.put("employees", allEmployees);
        model.put("newEmployee", new EmployeeDto());
        System.out.println("Get employees");
        return "employees";
    }

    @PostMapping("/employees")
    public String createEmployee(EmployeeDto employee) {

        allEmployees.add(employee);
        return "redirect:employees";

    }


    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Map<String, Object> model) {
        EmployeeDto employeeToEdit = allEmployees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (employeeToEdit == null) {
            return "redirect:/employees";
        }
        model.put("employee", employeeToEdit);
        return "employee-edit";
    }
    @PostMapping("/employees/update")
    public String updateEmployee(EmployeeDto employeeToUpdate) {
        int index = allEmployees.indexOf(allEmployees.stream().filter(emp -> emp.getId().equals(employeeToUpdate.getId())).findFirst().orElse(null));
        if (index != -1) {
            allEmployees.set(index, employeeToUpdate);
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Map<String, Object> model) {
        allEmployees.removeIf(employee -> employee.getId().equals(id));
        model.put("employees", allEmployees);
        return "redirect:/employees";
    }


    }









