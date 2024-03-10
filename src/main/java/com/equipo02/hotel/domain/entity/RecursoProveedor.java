package com.equipo02.hotel.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @file: RecursoProveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:06
 */

@Getter
@Setter
@Entity
@Table(name = "recursos_proveedores")
public class RecursoProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_proveedor", unique = true)
    private Long idProveedor;

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
