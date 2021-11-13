package uniandes.isis2304.bancandes.negocio;

public interface VOPuntoAtencion {
	
	public long getIdPuntoAtencion();
	
	public String getTipo();
	
	public String getLocalizacion();
	
	public long pertenece();
	@Override
	public String toString();
}
