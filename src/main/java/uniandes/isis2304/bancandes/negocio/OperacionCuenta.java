package uniandes.isis2304.bancandes.negocio;

public class OperacionCuenta implements VOOperacionCuenta {

	private long idOperacion;
	
	private String tipo;
	
	private long idTransaccion;
	
	private long idCuenta;
	
	public OperacionCuenta() {
		this.idOperacion = 0;
		this.tipo="";
		this.idTransaccion=0;
		this.idCuenta=0;
	}
	
	public OperacionCuenta(long idOperacion, String tipo, long idTransaccion, long idCuenta) {
		this.idOperacion = idOperacion;
		this.tipo=tipo;
		this.idTransaccion=idTransaccion;
		this.idCuenta=idCuenta;
	}
	
	@Override
	public long getIdOperacion() {
		return this.idOperacion;
	}
	
	public void setIdOperacion(long idOperacion) {
		this.idOperacion= idOperacion;
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}

	@Override
	public long getIdTransaccion() {
		return this.idTransaccion;
	}
	
	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	@Override
	public long getIdCuenta() {
		return this.idCuenta;
	}
	
	public void setIdCuenta(long idCuenta) {
		this.idCuenta = idCuenta;
	}

}
