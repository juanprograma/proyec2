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

import java.util.Date;

/**
 * Clase para modelar la relación GUSTAN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bebedor gusta de una bebida y viceversa.
 * Se modela mediante los identificadores del bebedor y de la bebida respectivamente.
 * Debe existir un bebedor con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * 
 * @author Germán Bravo
 */
public class Cdt implements VOCdt
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long idcdt;
	



	private long idcliente;
	private Date fechavencimiento;
	private long idpuntoatencion;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	public Cdt() {
		
		this.idcdt = 0;
		this.idcliente = 0;
		this.fechavencimiento = new Date(0);
		this.idpuntoatencion = 0;
	}
	/**
	 * Constructor con valores
	 * @param idBebedor - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param idBebida - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 */
	public Cdt(long idcdt, long idcliente, Date fechavencimiento, long idpuntoatencion) {
		super();
		this.idcdt = idcdt;
		this.idcliente = idcliente;
		this.fechavencimiento = fechavencimiento;
		this.idpuntoatencion = idpuntoatencion;
	}

	/**
	 * @return El idBebedor
	 */
	public long getIdcdt() {
		return idcdt;
	}
	public void setIdcdt(long idcdt) {
		this.idcdt = idcdt;
	}
	public long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}
	public Date getFechavencimiento() {
		return fechavencimiento;
	}
	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	public long getIdpuntoatencion() {
		return idpuntoatencion;
	}
	public void setIdpuntoatencion(long idpuntoatencion) {
		this.idpuntoatencion = idpuntoatencion;
	}
	@Override
	public String toString() {
		return "Cdt [idcdt=" + idcdt + ", idcliente=" + idcliente + ", fechavencimiento=" + fechavencimiento
				+ ", idpuntoatencion=" + idpuntoatencion + "]";
	}
	
}
