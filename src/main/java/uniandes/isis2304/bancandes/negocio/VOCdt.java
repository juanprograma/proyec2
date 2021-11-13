package uniandes.isis2304.bancandes.negocio;

import java.util.Date;

public interface VOCdt {

	public long getIdcdt();
	public long getIdcliente();
	public Date getFechavencimiento();
	public long getTasarendimiento();
	@Override
	public String toString();
}
