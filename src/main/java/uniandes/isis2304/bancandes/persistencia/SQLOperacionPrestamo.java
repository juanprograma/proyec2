package uniandes.isis2304.bancandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

public class SQLOperacionPrestamo {

	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLOperacionPrestamo (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long crearOperacionPrestamo (PersistenceManager pm, long idoperacion, String tipo, long idtransaccion, long idprestamo, long cuentaOrigen) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacionCuenta() + 
				"(idoperacion, tipo, idtransaccion, idprestamo, cuentaOrigen) values ( ?, ?, ?, ?,?)");
        q.setParameters(idoperacion, tipo, idtransaccion, idprestamo);
        return (long) q.executeUnique();
	}
	
	public List<Object[]> darOperacionPrestamoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoNoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA NOT BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoNoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoNoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionPrestamoNoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionPrestamo() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
}
	
		