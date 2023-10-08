package hu.cubix.hr.Szilard.config;


import jdk.jfr.Percentage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "smartemployee")
public class SmartEmployeeProperties {

    private List<Double> yearsLimits;
    private List<Integer> raisePercents;

    public List<Double> getYearsLimits() {
        return yearsLimits;
    }

    public void setYearsLimits(List<Double> yearsLimits) {
        this.yearsLimits = yearsLimits;
    }

    public List<Integer> getRaisePercents() {
        return raisePercents;
    }

    public void setRaisePercents(List<Integer> raisePercents) {
        this.raisePercents = raisePercents;
    }
}