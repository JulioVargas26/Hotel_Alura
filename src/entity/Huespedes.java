package entity;

import java.sql.Date;

public class Huespedes {
	
	private int codigo;
	private String nombre;
	private String apellido;
	private Date fec_nac;
	private int pais;
	private int telefono;
	private int reserva;
	
	
	
	public Huespedes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Huespedes(int codigo, String nombre, String apellido, Date fec_nac, int pais, int telefono, int reserva) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fec_nac = fec_nac;
		this.pais = pais;
		this.telefono = telefono;
		this.reserva = reserva;
	}


	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFec_nac() {
		return fec_nac;
	}
	public void setFec_nac(Date fec_nac) {
		this.fec_nac = fec_nac;
	}
	public int getPais() {
		return pais;
	}
	public void setPais(int pais) {
		this.pais = pais;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getReserva() {
		return reserva;
	}
	public void setReserva(int reserva) {
		this.reserva = reserva;
	}

}
