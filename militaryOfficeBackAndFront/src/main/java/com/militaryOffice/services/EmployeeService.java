package com.militaryOffice.services;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Employee;
import com.militaryOffice.repositories.EmployeeRepository;
import com.militaryOffice.security.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployeeByPassport(String passport){
        return employeeRepository.findByPassport(passport);
    }


    public Employee getEmployeeByAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails)authentication.getPrincipal();
        return getEmployeeByPassport(accountDetails.getAccount().getPassport());
    }

    public boolean isMainDoctor(){
        Employee employee = getEmployeeByAuthentication();
        return employee.getJobTitle().equals("Глав. врач");
    }

}
