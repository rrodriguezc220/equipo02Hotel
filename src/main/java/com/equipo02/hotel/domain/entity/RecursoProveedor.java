/**
 * @file: RecursoProveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:06
 */

package com.equipo02.hotel.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase RecursoProveedor.
 * Esta clase representa la relación entre un recurso y un proveedor en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "recursos_proveedores")
public class RecursoProveedor {

    /**
     * El ID de la relación entre el recurso y el proveedor.
     * Este campo es único para cada relación.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * El ID del proveedor.
     * Este campo representa el ID del proveedor en la relación.
     */
    @Column(name="id_proveedor", unique = true)
    private Long idProveedor;

    /**
     * Método para comparar la igualdad entre este objeto y otro.
     * Este método compara si el objeto proporcionado es igual a este objeto basándose en el ID del proveedor.
     *
     * @param obj El objeto a comparar.
     * @return Verdadero si los objetos son iguales, falso en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecursoProveedor)) {
            return false;
        }
        RecursoProveedor o = (RecursoProveedor) obj;
        return this.idProveedor != null && this.idProveedor.equals(o.idProveedor);
    }
}