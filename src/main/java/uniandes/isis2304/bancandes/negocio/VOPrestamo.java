package uniandes.isis2304.bancandes.negocio;

import java.sql.Date;

public interface VOPrestamo {

	public long getIdPrestamo();
	
	public int getMonto();
	
	public int getInteres();
	
	public int getSaldoPendiente();
	
	public Date getDiaPago();
	
	public int getValorCuotaMinima();
	
	public int getNumeroCuotas();
	
	public String getTipo();
	
	public long getIdCliente();
	
	public long getCuentasOficina();
	
}
