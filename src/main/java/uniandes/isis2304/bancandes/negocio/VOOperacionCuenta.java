package uniandes.isis2304.bancandes.negocio;



public interface VOOperacionCuenta {
	
	public long getIdOperacion();
	
	public String getTipo();
	
	public long getIdTransaccion();
	
	public long getIdCuenta();
	public long getCuentaorigen();
	public long getCuentadestino();
	@Override
	public String toString();
}
