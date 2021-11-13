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

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar el concepto BEBEDOR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Atiende implements VOAtiende
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del bebedor
	 */
	private long idcajero;	
	






	private long idpuntoatencion;
	
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	
	

	public Atiende() {
		
		this.idcajero = 0;
		this.idpuntoatencion = 0;
	}
	public Atiende(long idcajero, long idpuntoatencion) {
		
		this.idcajero = idcajero;
		this.idpuntoatencion = idpuntoatencion;
	}
	public long getIdcajero() {
		return idcajero;
	}


	public void setIdcajero(long idcajero) {
		this.idcajero = idcajero;
	}


	public long getIdpuntoatencion() {
		return idpuntoatencion;
	}
	public void setIdpuntoatencion(long idpuntoatencion) {
		this.idpuntoatencion = idpuntoatencion;
	}
	@Override
	public String toString() {
		return "Atiende [idcajero=" + idcajero + ", idpuntoatencion=" + idpuntoatencion + "]";
	}

	
}
