/**
 * @file: HuespedServiceImp.java
 * @author: (c)2024 rodriguez 
 * @created: Feb 29, 2024 8:50:55 PM
 */

package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.repositories.HuespedRepository;

@Service
public class HuespedServiceImp implements HuespedService {

	@Autowired
	private HuespedRepository huespedRep;

	@Override
	@Transactional
	public List<Huesped> listarHuespedes() {
		return huespedRep.findAll();
	}

	@Override
	@Transactional
	public Huesped buscarPorId(Long id){
		Optional<Huesped> huesped = huespedRep.findById(id);
		//if(huesped.isEmpty()) throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND) ;
		return huesped.get();
	}

	@Override
	@Transactional
	public Huesped grabar(Huesped huesped){
		/*if(huesped.getNombreHuesped().isBlank()) {
			throw new IllegalOperationException("El nombre del huesped no debe estar en blanco");
		}*/
		return huespedRep.save(huesped);
	}

	@Override
	@Transactional
	public Huesped actualizar(Long id, Huesped huesped){
		/*
		 * Optional<Huesped> huespedEntity = huespedRep.findById(id);
		 * if(huespedEntity.isEmpty()) throw new
		 * EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		 * if(huesped.getNombre().isBlank()) { throw new
		 * IllegalOperationException("El nombre del huesped no debe estar en blanco");
		 * }
		 */
		huesped.setIdHuesped(id);
		return huespedRep.save(huesped);
	}

	@Override
	@Transactional
	public void eliminar(Long id){
		/*
		 * Optional<Huesped> huespedEntity = huespedRep.findById(id);
		 * if(huespedEntity.isEmpty()) throw new
		 * EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		 * if(!huespedEntity.get().getDepartamento().getNombre().isBlank()) { throw new
		 * IllegalOperationException("No se puede eliminar, el huesped es aval de un cliente"
		 * ); }
		 */
		huespedRep.deleteById(id);
	}

	@Override
	@Transactional
	public Huesped asignarAval(Long idHuesped, Long idAval){
		Optional<Huesped> huespedEntity =  huespedRep.findById(idHuesped);
		Optional<Huesped> avalEntity = huespedRep.findById(idAval);
		huespedEntity.get().setAval(avalEntity.get());
		return huespedRep.save(huespedEntity.get());
	}
}
