/**
 * @file: RecursoController.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 14:53
 */

package com.equipo02.hotel.controllers;

import com.equipo02.hotel.domain.entity.Proveedor;
import com.equipo02.hotel.domain.entity.Recurso;
import com.equipo02.hotel.services.RecursoService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService service;



    @GetMapping
    public ResponseEntity<List<Recurso>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Recurso> o = service.porIdConProveedores(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping()
    public ResponseEntity<?> crear(@Valid @RequestBody Recurso recurso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Recurso recursoDb = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Recurso recurso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Recurso> o = service.porId(id);
        if (o.isPresent()) {
            Recurso recursoDb = o.get();
            recursoDb.setNombre(recurso.getNombre());
            recursoDb.setDescripcion(recurso.getDescripcion());
            recursoDb.setPrecio(recurso.getPrecio());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(recursoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Recurso> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(o.get().getIdRecurso());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-proveedor/{idRecurso}")
    public ResponseEntity<?> asignarProveedor(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.asignarProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el proveedor por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/crear-proveedor/{idRecurso}")
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.crearProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No se pudo crear el proveedor " +
                            "o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-proveedor/{idRecurso}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.eliminarProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el proveedor por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-recurso-proveedor/{id}")
    public ResponseEntity<?> eliminarRecursoProveedorPorId(@PathVariable Long id){
        service.eliminarRecursoProveedorPorId(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
