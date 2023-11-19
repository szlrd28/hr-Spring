package hu.cubix.hr.Szilard.mapper;


import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "title", source = "position.name")
    @Mapping(target = "entryDate", source = "dateOfStartWork")
    @Mapping(target="company.employees", ignore = true)
    EmployeeDto employeeToDto(Employee employee);


    @InheritInverseConfiguration
    Employee dtoToEmployee(EmployeeDto employeeDto);

    List<Employee> dtosToEmployees(List<EmployeeDto> employees);

}
