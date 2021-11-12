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
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Oficina implements VOOficina
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long idoficina;
	
	


	private String nombre;
	private String direccion;
	private long puntoatencion;
	private long gerente;
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	public Oficina() {
		
		this.idoficina = 0;
		this.nombre = "";
		this.direccion = "";
		this.puntoatencion = 0;
		this.gerente = 0;
	}


	public Oficina(long idoficina, String nombre, String direccion, long puntoatencion, long gerente) {
		
		this.idoficina = idoficina;
		this.nombre = nombre;
		this.direccion = direccion;
		this.puntoatencion = puntoatencion;
		this.gerente = gerente;
	}


	public long getIdoficina() {
		return idoficina;
	}


	public void setIdoficina(long idoficina) {
		this.idoficina = idoficina;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public long getPuntoatencion() {
		return puntoatencion;
	}


	public void setPuntoatencion(long puntoatencion) {
		this.puntoatencion = puntoatencion;
	}


	public long getGerente() {
		return gerente;
	}


	public void setGerente(long gerente) {
		this.gerente = gerente;
	}


	@Override
	public String toString() {
		return "Oficina [idoficina=" + idoficina + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", puntoatencion=" + puntoatencion + ", gerente=" + gerente + "]";
	}

	

}
