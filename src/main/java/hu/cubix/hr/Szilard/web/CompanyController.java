package hu.cubix.hr.Szilard.web;


import hu.cubix.hr.Szilard.dto.CompanyDto;
import hu.cubix.hr.Szilard.dto.EmployeeDto;

import hu.cubix.hr.Szilard.mapper.CompanyMapper;
import hu.cubix.hr.Szilard.mapper.EmployeeMapper;
import hu.cubix.hr.Szilard.model.AverageSalaryByPosition;
import hu.cubix.hr.Szilard.model.Company;
import hu.cubix.hr.Szilard.repository.CompanyRepository;
import hu.cubix.hr.Szilard.service.CompanyService;
import hu.cubix.hr.Szilard.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SalaryService salaryService;

    //1. megoldás: CompanyDto lemásolásával, de employees kivételével
    @GetMapping
    public List<CompanyDto> findAll(@RequestParam Optional<Boolean> full){
        List<Company> companies = companyService.findAll();
        return mapCompanies(companies, full);
    }

    //2. megoldás: JsonView-val
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> findAllWithEmployees(){
//		return new ArrayList<>(companies.values());
//	}
//
//	@GetMapping
//	@JsonView(Views.BaseData.class)
//	public List<CompanyDto> findAllWithoutEmployees(){
//		return new ArrayList<>(companies.values());
//	}
//
//	private CompanyDto mapCompanyWithoutEmployeees(CompanyDto c) {
//		return new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
//	}

    @GetMapping("/{id}")
    public CompanyDto findById(@PathVariable long id, @RequestParam Optional<Boolean> full) {
        Company company = getCompanyOrThrow(id);
        if(full.orElse(false)) {
            return companyMapper.companyToDto(company);
        } else {
            return companyMapper.companyToSummaryDto(company);
        }
    }

    @PostMapping
    public CompanyDto create(@RequestBody CompanyDto companyDto) {
        return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
    }


    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable long id, @RequestBody CompanyDto companyDto) {
        companyDto.setId(id);
        Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
        if (updatedCompany == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return companyMapper.companyToDto(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        companyService.delete(id);
    }

    @PostMapping("/{id}/employees")
    public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        Company company = companyService.addEmployee(id, companyMapper.dtoToEmployee(employeeDto));
        return companyMapper.companyToDto(company);
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public CompanyDto deleteEmployeeFromCompany(@PathVariable long id, @PathVariable long employeeId){
        Company company = companyService.deleteEmployee(id, employeeId);
        return companyMapper.companyToDto(company);
    }

    @PutMapping("/{id}/employees")
    public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> newEmployees) {
        Company company = companyService.replaceEmployees(id, companyMapper.dtosToEmployees(newEmployees));
        return companyMapper.companyToDto(company);
    }

    @GetMapping(params = "aboveSalary")
    public List<CompanyDto> getCompaniesAboveSalary(@RequestParam int aboveSalary,
                                                    @RequestParam Optional<Boolean> full) {
        List<Company> filteredCompanies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
        return mapCompanies(filteredCompanies, full);
    }

    @GetMapping(params = "aboveEmployeeCount")
    public List<CompanyDto> getCompaniesAboveEmployeeCount(@RequestParam int aboveEmployeeCount,
                                                           @RequestParam Optional<Boolean> full) {
        List<Company> filteredCompanies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeCount);
        return mapCompanies(filteredCompanies, full);
    }

    @GetMapping("/{id}/salaryStats")
    public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id) {
        return companyRepository.findAverageSalariesByPosition(id);
    }

    @PutMapping("/{id}/raiseMinSalary/{positionName}/{minSalary}")
    public void raiseMinSalary(@PathVariable long id, @PathVariable String positionName,
                               @PathVariable int minSalary) {
        salaryService.raiseMinSalary(positionName, minSalary, id);
    }


    private Company getCompanyOrThrow(long id) {
        return companyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private List<CompanyDto> mapCompanies(List<Company> companies, Optional<Boolean> full) {
        if(full.orElse(false)) {
            return companyMapper.companiesToDtos(companies);
        } else {
            return companyMapper.companiesToSummaryDtos(companies);
        }
    }



}
