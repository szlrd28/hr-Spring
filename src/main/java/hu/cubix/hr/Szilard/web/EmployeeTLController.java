package hu.cubix.hr.Szilard.web;



import hu.cubix.hr.Szilard.dto.EmployeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class EmployeeTLController {

    private List<EmployeeDto> allEmployees = new ArrayList<>();
    {
        System.out.println("Get employees");
    allEmployees.add(new EmployeeDto(1L, "József", "Mérnök", 400000, LocalDateTime.now().minusYears(1)));

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

}

