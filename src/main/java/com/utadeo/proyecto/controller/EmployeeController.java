package com.utadeo.proyecto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.proyecto.model.Employee;
import com.utadeo.proyecto.service.IEmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private IEmployeeService employeeService;

    EmployeeController(IEmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("create/")
    public Employee Store(@RequestBody Employee employee){
        return this.employeeService.creatEmployee(employee);
    }

    @PutMapping("edit/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);        
    }

    @GetMapping("/{id}/")
    public Employee show(@PathVariable("id") Long id){
        return this.employeeService.getEmployee(id);
    }

    @GetMapping("all/")
    public List<Employee> GetAllUsers(){
		return employeeService.getAllEmployee();
	}

    @DeleteMapping("/delete/{id}/")
	public void deleteUser(@PathVariable("id") Long id) {
		this.employeeService.deleteEmployee(id);
	}


}
