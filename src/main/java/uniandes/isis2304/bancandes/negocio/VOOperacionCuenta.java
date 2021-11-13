package uniandes.isis2304.bancandes.negocio;

import java.sql.Date;

public interface VOOperacionCuenta {
	
	public long getIdOperacion();
	
	public String getTipo();
	
	public long getIdTransaccion();
	
	public long getIdCuenta();
	public Date getCuentaorigen();
	public Date getCuentadestino();
	@Override
	public String toString();
}
