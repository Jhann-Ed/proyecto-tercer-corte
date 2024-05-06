package com.utadeo.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utadeo.proyecto.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee , Long>{

}
