package com.utadeo.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "status_id")
    private Long id;

    @JoinColumn(name = "name")
    private String name;
    
}
