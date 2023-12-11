package com.militaryOffice.repositories;

import com.militaryOffice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByPassport(String passport);
}
