package com.utadeo.proyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.utadeo.proyecto.model.Employee;
import com.utadeo.proyecto.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImplementation implements IEmployeeService{

    private IEmployeeRepository employeeRepository;

    EmployeeServiceImplementation(IEmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee creatEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        if(this.employeeRepository.existsById(id)){
            employee.setId(id);
            return this.employeeRepository.save(employee);
        }

        return null;
    }

    @Override
    public Employee getEmployee(Long id) {
       return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }

    

}
