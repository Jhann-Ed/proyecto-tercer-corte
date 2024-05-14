package com.utadeo.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone")
    private long phone;

    @Column(name = "salary")
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "position")
    private Position PositionId;

    public Employee() {
    }

    public Employee(String name, String lastName, String email, String adress, long telefono){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.phone = telefono;
    }

    public Employee(String name, String lastName, String email, String adress, long telefono, Long salary,
            Position positionId) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.phone = telefono;
        this.salary = salary;
        PositionId = positionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getphone() {
        return phone;
    }

    public void setphone(long phone) {
        this.phone = phone;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Position getPositionId() {
        return PositionId;
    }

    public void setPositionId(Position positionId) {
        PositionId = positionId;
    }

}
