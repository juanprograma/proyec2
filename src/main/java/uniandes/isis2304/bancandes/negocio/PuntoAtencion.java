package uniandes.isis2304.bancandes.negocio;

public class PuntoAtencion implements VOPuntoAtencion{

	private long idPuntoAtencion;
	
	private String tipo;
	
	private String localizacion;
	
	private long pertenece;
	
	public PuntoAtencion() {
		this.setIdPuntoAtencion(0);
		this.setTipo("");
		this.setLocalizacion("");
		this.setPertenece(0);
	}
	
	public PuntoAtencion(long idPuntoAtencion, String tipo, String localizacion, long pertenece) {
		this.setIdPuntoAtencion(idPuntoAtencion);
		this.setTipo(tipo);
		this.setLocalizacion(localizacion);
		this.setPertenece(pertenece);
	}
	
	@Override
	public long getIdPuntoAtencion() {
		return this.idPuntoAtencion;
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public String getLocalizacion() {
		return this.localizacion;
	}

	@Override
	public long pertenece() {
		return this.pertenece;
	}

	public void setIdPuntoAtencion(long idPuntoAtencion) {
		this.idPuntoAtencion = idPuntoAtencion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public void setPertenece(long pertenece) {
		this.pertenece = pertenece;
	}
	
}
