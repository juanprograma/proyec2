package uniandes.isis2304.bancandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPuntoAtencion {
	
	private PersistenciaBancandes pp;
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	public  SQLPuntoAtencion (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public List<Object[]> buscarOperacionesEnAccionesEnDosPuntosAtencion(PersistenceManager pm, long primerPunto, long segundoPunto){
		
		String sql = "SELECT U.TIPODOCUMENTO, U.NUMERODOCUMENTO, U.NOMBRE, U.TIPO, U.DIRECCIONELECTRONICA, P.IDPUNTOATENCION, O.TIPO, T.VALOR, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaPuntoAtencion() + " P, ";
		sql += pp.darTablaTransaccion() + " T, ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaUsuario() + " U ";
		sql += "WHERE ";
		sql += "(P.IDPUNTOATENCION = ? OR P.IDPUNTOATENCION = ?) ";
		sql += "AND T.OPERACIONESPUNTO = P.IDPUNTOATENCION ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.IDUSUARIO = U.NUMERODOCUMENTO ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primerPunto, segundoPunto);
		return q.executeList();
	}
	
	public List<Object[]> buscarOperacionesEnCDTEnDosPuntosAtencion(PersistenceManager pm, long primerPunto, long segundoPunto){
		
		String sql = "SELECT U.TIPODOCUMENTO, U.NUMERODOCUMENTO, U.NOMBRE, U.TIPO, U.DIRECCIONELECTRONICA, P.IDPUNTOATENCION, O.TIPO, T.VALOR, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaPuntoAtencion() + " P, ";
		sql += pp.darTablaTransaccion() + " T, ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaUsuario() + " U ";
		sql += "WHERE ";
		sql += "(P.IDPUNTOATENCION = ? OR P.IDPUNTOATENCION = ?) ";
		sql += "AND T.OPERACIONESPUNTO = P.IDPUNTOATENCION ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.IDUSUARIO = U.NUMERODOCUMENTO ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primerPunto, segundoPunto);
		return q.executeList();
	}
	
	public List<Object[]> buscarOperacionesEnCuentaEnDosPuntosAtencion(PersistenceManager pm, long primerPunto, long segundoPunto){
		
		String sql = "SELECT U.TIPODOCUMENTO, U.NUMERODOCUMENTO, U.NOMBRE, U.TIPO, U.DIRECCIONELECTRONICA, P.IDPUNTOATENCION, O.TIPO, T.VALOR, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaPuntoAtencion() + " P, ";
		sql += pp.darTablaTransaccion() + " T, ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaUsuario() + " U ";
		sql += "WHERE ";
		sql += "(P.IDPUNTOATENCION = ? OR P.IDPUNTOATENCION = ?) ";
		sql += "AND T.OPERACIONESPUNTO = P.IDPUNTOATENCION ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.IDUSUARIO = U.NUMERODOCUMENTO ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primerPunto, segundoPunto);
		return q.executeList();
	}
	
	public List<Object[]> buscarOperacionesEnDepositosDeInversionEnDosPuntosAtencion(PersistenceManager pm, long primerPunto, long segundoPunto){
		
		String sql = "SELECT U.TIPODOCUMENTO, U.NUMERODOCUMENTO, U.NOMBRE, U.TIPO, U.DIRECCIONELECTRONICA, P.IDPUNTOATENCION, O.TIPO, T.VALOR, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaPuntoAtencion() + " P, ";
		sql += pp.darTablaTransaccion() + " T, ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaUsuario() + " U ";
		sql += "WHERE ";
		sql += "(P.IDPUNTOATENCION = ? OR P.IDPUNTOATENCION = ?) ";
		sql += "AND T.OPERACIONESPUNTO = P.IDPUNTOATENCION ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.IDUSUARIO = U.NUMERODOCUMENTO ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primerPunto, segundoPunto);
		return q.executeList();
	}
	
	public List<Object[]> buscarOperacionesEnPrestamoEnDosPuntosAtencion(PersistenceManager pm, long primerPunto, long segundoPunto){
		
		String sql = "SELECT U.TIPODOCUMENTO, U.NUMERODOCUMENTO, U.NOMBRE, U.TIPO, U.DIRECCIONELECTRONICA, P.IDPUNTOATENCION, O.TIPO, T.VALOR, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaPuntoAtencion() + " P, ";
		sql += pp.darTablaTransaccion() + " T, ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaUsuario() + " U ";
		sql += "WHERE ";
		sql += "(P.IDPUNTOATENCION = ? OR P.IDPUNTOATENCION = ?) ";
		sql += "AND T.OPERACIONESPUNTO = P.IDPUNTOATENCION ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.IDUSUARIO = U.NUMERODOCUMENTO ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primerPunto, segundoPunto);
		return q.executeList();
	}

}
