package hu.cubix.hr.Szilard.config;

import hu.cubix.hr.Szilard.service.DefaultEmployeeService;
import hu.cubix.hr.Szilard.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!smart")
public class DefaultConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new DefaultEmployeeService();
    }
}
