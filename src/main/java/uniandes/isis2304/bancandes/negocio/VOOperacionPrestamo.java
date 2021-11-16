package uniandes.isis2304.bancandes.negocio;

public interface VOOperacionPrestamo {
	
	public long getIdOperacion();
	
	public String getTipo();
	
	public long getIdTransaccion();
	
	public long getIdPrestamo();

	@Override
	public String toString();

}
