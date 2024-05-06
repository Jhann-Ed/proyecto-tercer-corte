package com.utadeo.proyecto.service;

import java.util.List;

import com.utadeo.proyecto.model.Employee;

public interface IEmployeeService {

    Employee creatEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployee ();
    void deleteEmployee (Long id);
}
