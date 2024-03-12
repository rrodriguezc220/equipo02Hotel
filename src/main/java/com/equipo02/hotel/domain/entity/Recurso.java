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

/**
 * Clase Recurso.
 * Esta clase representa un recurso en el sistema.
 */
@Data
@Entity
@Table(name = "recursos")
public class Recurso {

    /**
     * El ID del recurso.
     * Este campo es único para cada recurso.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecurso;

    /**
     * El nombre del recurso.
     * Este campo representa el nombre del recurso.
     */
    @NotEmpty
    private String nombre;

    /**
     * La descripción del recurso.
     * Este campo representa la descripción del recurso.
     */
    @NotEmpty
    private String descripcion;

    /**
     * El precio del recurso.
     * Este campo representa el precio del recurso.
     */
    @NotNull
    private Double precio;

    /**
     * La lista de proveedores de recursos.
     * Este campo representa la relación entre el recurso y sus proveedores.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recurso_id")
    private List<RecursoProveedor> recursoProveedores;

    /**
     * La lista de proveedores.
     * Este campo es transitorio y representa la lista de proveedores asociados a este recurso.
     */
    @Transient
    private List<Proveedor> proveedores;

    /**
     * Constructor de la clase Recurso.
     * Inicializa las listas de proveedores de recursos y proveedores.
     */
    public Recurso() {
        recursoProveedores = new ArrayList<>();
        proveedores = new ArrayList<>();
    }

    /**
     * Método para agregar un proveedor de recursos.
     * Este método agrega un proveedor de recursos a la lista de proveedores de recursos.
     *
     * @param recursoProveedor El proveedor de recursos a agregar.
     */
    public void addRecursoProveedor(RecursoProveedor recursoProveedor) {
        recursoProveedores.add(recursoProveedor);
    }

    /**
     * Método para eliminar un proveedor de recursos.
     * Este método elimina un proveedor de recursos de la lista de proveedores de recursos.
     *
     * @param recursoProveedor El proveedor de recursos a eliminar.
     */
    public void removeRecursoProveedor(RecursoProveedor recursoProveedor) {
        recursoProveedores.remove(recursoProveedor);
    }
}