package uniandes.isis2304.bancandes.negocio;

public class Operacioncdt implements VOOperacioncdt {

	private long idoperacion;
	

	private String tipo;
	private long idtransaccion;
	private long idcuenta;
	
	public Operacioncdt() {
		
		this.idoperacion = 0;
		this.tipo = "";
		this.idtransaccion = 0;
		this.idcuenta = 0;
	}
	public Operacioncdt(long idoperacion, String tipo, long idtransaccion, long idcuenta) {
		
		this.idoperacion = idoperacion;
		this.tipo = tipo;
		this.idtransaccion = idtransaccion;
		this.idcuenta = idcuenta;
	}
	
	public long getIdoperacion() {
		return idoperacion;
	}
	public void setIdoperacion(long idoperacion) {
		this.idoperacion = idoperacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getIdtransaccion() {
		return idtransaccion;
	}
	public void setIdtransaccion(long idtransaccion) {
		this.idtransaccion = idtransaccion;
	}
	public long getIdcuenta() {
		return idcuenta;
	}
	public void setIdcuenta(long idcuenta) {
		this.idcuenta = idcuenta;
	}
	@Override
	public String toString() {
		return "Operacioncdt [idoperacion=" + idoperacion + ", tipo=" + tipo + ", idtransaccion=" + idtransaccion
				+ ", idcuenta=" + idcuenta + "]";
	}

}
