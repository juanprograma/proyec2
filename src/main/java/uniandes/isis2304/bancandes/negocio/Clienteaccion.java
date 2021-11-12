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

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;



/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class Clienteaccion implements VOClienteaccion
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private long id;
	


	private long idaccion;
    private long idcliente;
	private long cantidad;
	

	
	public Clienteaccion() {
		
		this.id = 0;
		this.idaccion = 0;
		this.idcliente = 0;
		this.cantidad = 0;
	}

	public Clienteaccion(long id, long idaccion, long idcliente, long cantidad) {
		super();
		this.id = id;
		this.idaccion = idaccion;
		this.idcliente = idcliente;
		this.cantidad = cantidad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdaccion() {
		return idaccion;
	}

	public void setIdaccion(long idaccion) {
		this.idaccion = idaccion;
	}

	public long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "Clienteaccion [id=" + id + ", idaccion=" + idaccion + ", idcliente=" + idcliente + ", cantidad="
				+ cantidad + "]";
}
}