package uniandes.isis2304.bancandes.negocio;



public class OperacionCuenta implements VOOperacionCuenta {

	private long idOperacion;
	
	

	
	private String tipo;
	
	private long idTransaccion;
	
	private long idCuenta;
	
	public long cuentaorigen;
	

	public long cuentadestino;
	
	public OperacionCuenta() {
		this.idOperacion = 0;
		this.tipo="";
		this.idTransaccion=0;
		this.idCuenta=0;
		this.cuentaorigen= 0;
		this.cuentadestino= 0;
	}
	public OperacionCuenta(long idOperacion, String tipo, long idTransaccion, long idCuenta, long cuentaorigen,
			long cuentadestino) {
		super();
		this.idOperacion = idOperacion;
		this.tipo = tipo;
		this.idTransaccion = idTransaccion;
		this.idCuenta = idCuenta;
		this.cuentaorigen = cuentaorigen;
		this.cuentadestino = cuentadestino;
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
		
	}public long getCuentaorigen() {
		return cuentaorigen;
	}

	public void setCuentaorigen(long cuentaorigen) {
		this.cuentaorigen = cuentaorigen;
	}

	public long getCuentadestino() {
		return cuentadestino;
	}

	public void setCuentadestino(long cuentadestino) {
		this.cuentadestino = cuentadestino;
	}
	@Override
	public String toString() {
		return "OperacionCuenta [idOperacion=" + idOperacion + ", tipo=" + tipo + ", idTransaccion=" + idTransaccion
				+ ", idCuenta=" + idCuenta + ", cuentaorigen=" + cuentaorigen + ", cuentadestino=" + cuentadestino
				+ "]";
	}
}
