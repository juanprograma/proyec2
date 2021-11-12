/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.bancandes.negocio;

/**
 * Clase para modelar el concepto BEBIDA del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Cajeroatm implements VOCajeroatm
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private long idcajeroatm;
	

	


	private long idpuntoatencion;
	
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	public Cajeroatm() {
		
		this.idcajeroatm = 0;
		this.idpuntoatencion = 0;
	}

	
	public Cajeroatm(long idcajeroatm, long idpuntoatencion) {
		
		this.idcajeroatm = idcajeroatm;
		this.idpuntoatencion = idpuntoatencion;
	}


	public long getIdcajeroatm() {
		return idcajeroatm;
	}

	public void setIdcajeroatm(long idcajeroatm) {
		this.idcajeroatm = idcajeroatm;
	}

	public long getIdpuntoatencion() {
		return idpuntoatencion;
	}

	public void setIdpuntoatencion(long idpuntoatencion) {
		this.idpuntoatencion = idpuntoatencion;
	}

	

	@Override
	public String toString() {
		return "Cajeroatm [idcajeroatm=" + idcajeroatm + ", idpuntoatencion=" + idpuntoatencion + "]";
	}

}
