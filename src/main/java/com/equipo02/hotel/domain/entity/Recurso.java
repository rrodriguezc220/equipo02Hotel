/**
 * @file: Recurso.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:06
 */

package com.equipo02.hotel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recursos")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecurso;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String descripcion;

    @NotNull
    private Double precio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recurso_id")
    private List<RecursoProveedor> recursoProveedores;

    @Transient
    private List<Proveedor> proveedores;


    public Recurso() {
        recursoProveedores = new ArrayList<>();
        proveedores = new ArrayList<>();
    }

    public void addRecursoProveedor(RecursoProveedor recursoProveedor) {
        recursoProveedores.add(recursoProveedor);
    }

    public void removeRecursoProveedor(RecursoProveedor recursoProveedor) {
        recursoProveedores.remove(recursoProveedor);
    }





}
