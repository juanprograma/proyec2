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
 * Clase para modelar la relación SIRVEN del negocio de los Parranderos:
 * Cada objeto de esta clase representa el hecho que un bar sirve una bebida y viceversa.
 * Se modela mediante los identificadores del bar y de la bebida respectivamente
 * Debe existir un bar con el identificador dado
 * Debe existir una bebida con el identificador dado 
 * Adicionalmente contiene el horario (DIURNO, NOCTURNO, TODOS) en el cual el bar sirve la bebida
 * 
 * @author Germán Bravo
 */
public class Cuenta implements VOCuenta
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long idcuenta;
	

	private String activa;
	private Date fechacreacion;
	private long saldo;
	private  Date fechaultimomov;
	private String tipo;
	private long idcliente;
	private long cuentasoficina;
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	public Cuenta() {
		
		this.idcuenta = 0;
		this.activa = "";
		this.fechacreacion = new Date(0);
		this.saldo = 0;
		this.fechaultimomov = new Date (0);
		this.tipo = "";
		this.idcliente = 0;
		this.cuentasoficina = 0;
	}
	public Cuenta(long idcuenta, String activa, Date fechacreacion, long saldo, Date fechaultimomov, String tipo,
			long idcliente, long cuentasoficina) {
		
		this.idcuenta = idcuenta;
		this.activa = activa;
		this.fechacreacion = fechacreacion;
		this.saldo = saldo;
		this.fechaultimomov = fechaultimomov;
		this.tipo = tipo;
		this.idcliente = idcliente;
		this.cuentasoficina = cuentasoficina;
	}
	


	public long getIdcuenta() {
		return idcuenta;
	}
	public void setIdcuenta(long idcuenta) {
		this.idcuenta = idcuenta;
	}
	public String getActiva() {
		return activa;
	}
	public void setActiva(String activa) {
		this.activa = activa;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public long getSaldo() {
		return saldo;
	}
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	public Date getFechaultimomov() {
		return fechaultimomov;
	}
	public void setFechaultimomov(Date fechaultimomov) {
		this.fechaultimomov = fechaultimomov;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}
	public long getCuentasoficina() {
		return cuentasoficina;
	}
	public void setCuentasoficina(long cuentasoficina) {
		this.cuentasoficina = cuentasoficina;
	}

	@Override
	public String toString() {
		return "Cuenta [idcuenta=" + idcuenta + ", activa=" + activa + ", fechacreacion=" + fechacreacion + ", saldo="
				+ saldo + ", fechaultimomov=" + fechaultimomov + ", tipo=" + tipo + ", idcliente=" + idcliente
				+ ", cuentasoficina=" + cuentasoficina + "]";
	}
}
