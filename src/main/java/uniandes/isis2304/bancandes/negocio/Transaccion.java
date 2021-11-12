package uniandes.isis2304.bancandes.negocio;

import java.sql.Timestamp;

public class Transaccion implements VOTransaccion {
	
	private long idTransaccion;
	
	private long idUsuario;
	
	private Timestamp fechaHora;
	
	private int valor;
	
	private long operacionesPunto;
	
	public Transaccion() {
		this.setIdTransaccion(0);
		this.setIdUsuario(0);
		this.setFechaHora(null);
		this.setValor(0);
		this.setOperacionesPunto(0);
	}
	
	public Transaccion(long idTransaccion, long idUsuario, Timestamp fechaHora, int valor, long operacionesPunto) {
		this.setIdTransaccion(idTransaccion);
		this.setIdUsuario(idUsuario);
		this.setFechaHora(fechaHora);
		this.setValor(valor);
		this.setOperacionesPunto(operacionesPunto);
	}

	@Override
	public long getIdTransaccion() {
		return this.idTransaccion;
	}

	@Override
	public long getIdUsuario() {
		return this.idUsuario;
	}

	@Override
	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	@Override
	public int getValor() {
		return this.valor;
	}

	@Override
	public long getOperacionesPunto() {
		return this.operacionesPunto;
	}

	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public void setOperacionesPunto(long operacionesPunto) {
		this.operacionesPunto = operacionesPunto;
	}

}
