package com.equipo02.hotel.clients;

import com.equipo02.hotel.domain.entity.Proveedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @file: ProveedorClienteRest.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:06
 */
@FeignClient(name="equipo02hotel-proveedores", url="localhost:8005/api/proveedores")
public interface ProveedorClienteRest{

    @GetMapping("/{id}")
    Proveedor detalle(@PathVariable Long id);

    @PostMapping()
    Proveedor crear(@RequestBody Proveedor proveedor);

    @GetMapping("/proveedor-por-recurso")
    List<Proveedor> obtenerProveedoresPorRecurso(@RequestParam Iterable<Long> ids);

}
