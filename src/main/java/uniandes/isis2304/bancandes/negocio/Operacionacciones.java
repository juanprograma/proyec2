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


public class Operacionacciones implements VOOperacionacciones{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long idoperacion;
	

	
	private String tipo;
	private long idtransaccion;
	private long idclienteaccion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	public Operacionacciones() {
		
		this.idoperacion =0;
		this.tipo = "";
		this.idtransaccion = 0;
		this.idclienteaccion = 0;
	}
	public Operacionacciones(long idoperacion, String tipo, long idtransaccion, long idclienteaccion) {
		
		this.idoperacion = idoperacion;
		this.tipo = tipo;
		this.idtransaccion = idtransaccion;
		this.idclienteaccion = idclienteaccion;
	}

	public long getIdoperacion() {
		return idoperacion;
	}

	public void setIdoperacion(long idoperacion) {
		this.idoperacion = idoperacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getIdtransaccion() {
		return idtransaccion;
	}

	public void setIdtransaccion(long idtransaccion) {
		this.idtransaccion = idtransaccion;
	}

	public long getIdclienteaccion() {
		return idclienteaccion;
	}

	public void setIdclienteaccion(long idclienteaccion) {
		this.idclienteaccion = idclienteaccion;
	}
	@Override
	public String toString() {
		return "Operacionacciones [idoperacion=" + idoperacion + ", tipo=" + tipo + ", idtransaccion=" + idtransaccion
				+ ", idclienteaccion=" + idclienteaccion + "]";
	}
}
