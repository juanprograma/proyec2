package uniandes.isis2304.bancandes.negocio;

import java.sql.Timestamp;

public interface VOTransaccion {
	
	public long getIdTransaccion();
	
	public long getIdUsuario();
	
	public Timestamp getFechaHora();
	
	public int getValor();
	
	public long getOperacionesPunto(); 
	@Override
	public String toString();

}
