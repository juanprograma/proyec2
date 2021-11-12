package uniandes.isis2304.bancandes.negocio;

import java.sql.Date;

public class Prestamo implements VOPrestamo{
	
	private long idPrestamo;
	
	private int monto;
	
	private int interes;
	
	private int saldoPendiente;
	
	private Date diaPago;
	
	private int valorCuotaMinima;
	
	private int numeroCuotas;
	
	private String tipo;
	
	private long idCliente;
	
	private long cuentasOficina;
	
	public Prestamo() {
		this.idPrestamo=0;
		this.monto=0;
		this.interes=0;
		this.saldoPendiente=0;
		this.diaPago=null;
		this.valorCuotaMinima=0;
		this.numeroCuotas=0;
		this.tipo="";
		this.idCliente=0;
		this.cuentasOficina=0;
	}
	
	public Prestamo(long idPrestamo, int monto, int interes, int saldoPendiente, Date diaPago, int valorCuotaMinima, int numeroCuotas, String tipo, long idCliente, long cuentasOficina) {
		this.idPrestamo=idPrestamo;
		this.monto=monto;
		this.interes=interes;
		this.saldoPendiente=saldoPendiente;
		this.diaPago=diaPago;
		this.valorCuotaMinima=valorCuotaMinima;
		this.numeroCuotas=numeroCuotas;
		this.tipo=tipo;
		this.idCliente=idCliente;
		this.cuentasOficina=cuentasOficina;
	}
	

	@Override
	public long getIdPrestamo() {
		return this.idPrestamo;
	}

	@Override
	public int getMonto() {
		return this.monto;
	}

	@Override
	public int getInteres() {
		return this.interes;
	}

	@Override
	public int getSaldoPendiente() {
		return this.saldoPendiente;
	}

	@Override
	public Date getDiaPago() {
		return this.diaPago;
	}

	@Override
	public int getValorCuotaMinima() {
		return this.valorCuotaMinima;
	}

	@Override
	public int getNumeroCuotas() {
		return this.numeroCuotas;
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public long getIdCliente() {
		return this.idCliente;
	}

	@Override
	public long getCuentasOficina() {
		return this.cuentasOficina;
	}

	public void setIdPrestamo(long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}

	public void setSaldoPendiente(int saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}

	public void setDiaPago(Date diaPago) {
		this.diaPago = diaPago;
	}

	public void setValorCuotaMinima(int valorCuotaMinima) {
		this.valorCuotaMinima = valorCuotaMinima;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public void setCuentasOficina(long cuentasOficina) {
		this.cuentasOficina = cuentasOficina;
	}
	
}
