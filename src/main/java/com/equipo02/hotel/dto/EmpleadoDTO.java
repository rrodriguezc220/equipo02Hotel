package com.equipo02.hotel.dto;

import com.equipo02.hotel.domain.Empleado;


public class EmpleadoDTO {

	private Long idEmpleado;
	private String dniEmpleado;
	private String nombreEmpleado;
	private String direccionEmpleado;
	private String telefonoEmpleado;
	private String correoEmpleado;
	private Empleado supervisor;
	//private List<Reserva> reservas = new ArrayList();

	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDniEmpleado() {
		return dniEmpleado;
	}
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}
	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}
	public String getTelefonoEmpleado() {
		return telefonoEmpleado;
	}
	public void setTelefonoEmpleado(String telefonoEmpleado) {
		this.telefonoEmpleado = telefonoEmpleado;
	}
	public String getCorreoEmpleado() {
		return correoEmpleado;
	}
	public void setCorreoEmpleado(String correoEmpleado) {
		this.correoEmpleado = correoEmpleado;
	}
/*	public Empleado getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Empleado supervisor) {
		this.supervisor = supervisor;
	}*/
	/*public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}*/
}
