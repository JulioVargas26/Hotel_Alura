package entity;

import java.sql.Date;

public class Reserva {
	
	private int codigo;
	private Date fec_ent;
	private Date fec_sal;
	private int valor;
	private int pago;

	
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reserva(int codigo, Date fec_ent, Date fec_sal, int valor, int pago) {
		super();
		this.codigo = codigo;
		this.fec_ent = fec_ent;
		this.fec_sal = fec_sal;
		this.valor = valor;
		this.pago = pago;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFec_ent() {
		return fec_ent;
	}
	public void setFec_ent(Date fec_ent) {
		this.fec_ent = fec_ent;
	}
	public Date getFec_sal() {
		return fec_sal;
	}
	public void setFec_sal(Date fec_sal) {
		this.fec_sal = fec_sal;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getPago() {
		return pago;
	}
	public void setPago(int pago) {
		this.pago = pago;
	}
	
	
	
	
	

}
