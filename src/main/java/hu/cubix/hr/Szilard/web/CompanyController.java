package hu.cubix.hr.Szilard.web;

import com.fasterxml.jackson.annotation.JsonView;
import hu.cubix.hr.Szilard.dto.CompanyDto;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import hu.cubix.hr.Szilard.dto.Views;
import hu.cubix.hr.Szilard.mapper.CompanyMapper;
import hu.cubix.hr.Szilard.model.Company;
import hu.cubix.hr.Szilard.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //1. megoldás: CompanyDto lemásolásával, de employees kivételével
    @GetMapping
    public List<CompanyDto> findAll(@RequestParam Optional<Boolean> full){
        List<Company> companies = companyService.findAll();
        if(full.orElse(false)) {
            return companyMapper.companiesToDtos(companies);
        } else {
            return companyMapper.companiesToSummaryDtos(companies);
        }
    }


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


    private Company getCompanyOrThrow(long id) {
        return companyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}
