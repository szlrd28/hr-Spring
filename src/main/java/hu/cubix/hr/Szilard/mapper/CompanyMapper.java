package hu.cubix.hr.Szilard.mapper;


import hu.cubix.hr.Szilard.dto.CompanyDto;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.model.Company;
import hu.cubix.hr.Szilard.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    public CompanyDto companyToDto(Company company);

    public List<CompanyDto> companiesToDtos(List<Company> companies);

    public Company dtoToCompany(CompanyDto companyDto);

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "title", source = "position.name")
    @Mapping(target = "entryDate", source = "dateOfStartWork")
    @Mapping(target = "company", ignore = true)
    EmployeeDto employeeToDto(Employee employee);


    @InheritInverseConfiguration
    Employee dtoToEmployee(EmployeeDto employeeDto);

    @Mapping(target = "employees", ignore = true)
    @Named("summary")
    public CompanyDto companyToSummaryDto(Company company);

    @IterableMapping(qualifiedByName = "summary")
    public List<CompanyDto> companiesToSummaryDtos(List<Company> companies);

    public List<Employee> dtosToEmployees(List<EmployeeDto> employees);
}
