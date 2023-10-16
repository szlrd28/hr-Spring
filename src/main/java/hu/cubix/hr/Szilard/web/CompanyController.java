package hu.cubix.hr.Szilard.web;

import hu.cubix.hr.Szilard.dto.CompanyDto;
import hu.cubix.hr.Szilard.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private List<CompanyDto> allCompanies = new ArrayList<>();
    //private Map<Long, EmployeeDto> allCompanies = new HashMap<>();
    List<EmployeeDto> employeesList = new ArrayList<>();
    {
        allCompanies.add(new CompanyDto(1,221, "Ferenc Liszt Airport", "BUDAPEST", employeesList));
    }

//Az összes alkalmazott visszaadása
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }
    //Adott id-jű alkalmazott visszaadása
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable int id) {
        CompanyDto companyDto = allCompanies.get(id);
        if(companyDto != null)
            return ResponseEntity.ok(companyDto);
        else
            return ResponseEntity.notFound().build();

    }
//   Új alkalmazott felvétele
    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
        allCompanies.add(companyDto);
        return new ResponseEntity<>(companyDto, HttpStatus.CREATED);
    }
    //Meglévő alkalmazott módosítására
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable int id, @RequestBody CompanyDto companyDto) {
        if (id >= 0 && id < allCompanies.size()) {
            companyDto.setId(id);
            allCompanies.set(id, companyDto);
            return ResponseEntity.ok(companyDto);
        }
        return ResponseEntity.notFound().build();
    }
 //Meglévő alkalmazott törlésére•
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        allCompanies.remove(id);
    }



}
