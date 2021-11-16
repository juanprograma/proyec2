package uniandes.isis2304.bancandes.negocio;

public class OperacionPrestamo implements VOOperacionPrestamo {

	private long idOperacion;
	
	

	private String tipo;
	
	private long idTransaccion;
	
	private long idPrestamo;
	private long cuentaOrigen;
	public OperacionPrestamo() {
		this.idOperacion=0;
		this.tipo="";
		this.idTransaccion=0;
		this.idPrestamo=0;
		this.cuentaOrigen=0;
	}
	
	public OperacionPrestamo(long idOperacion, String tipo, long idTransaccion, long idPrestamo,long cuentaOrigen) {
		this.idOperacion=idOperacion;
		this.tipo=tipo;
		this.idTransaccion=idTransaccion;
		this.idPrestamo=idPrestamo;
		this.cuentaOrigen=cuentaOrigen;
	}
	
	@Override
	public long getIdOperacion() {
		return this.idOperacion;
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public long getIdTransaccion() {
		return this.idTransaccion;
	}

	@Override
	public long getIdPrestamo() {
		return this.idPrestamo;
	}

	public void setIdOperacion(long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public void setIdPrestamo(long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public long getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(long cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	@Override
	public String toString() {
		return "OperacionPrestamo [idOperacion=" + idOperacion + ", tipo=" + tipo + ", idTransaccion=" + idTransaccion
				+ ", idPrestamo=" + idPrestamo + ", cuentaOrigen=" + cuentaOrigen + "]";
	}


}
