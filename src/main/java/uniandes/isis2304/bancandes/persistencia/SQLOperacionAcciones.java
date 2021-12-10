package uniandes.isis2304.bancandes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLOperacionAcciones {
	
	private final static String SQL = PersistenciaBancandes.SQL;

	private PersistenciaBancandes pp;
	
	public  SQLOperacionAcciones (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public List<Object[]> darOperacionAccionesPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesNoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA NOT BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesNoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesNoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionAccionesNoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionAcciones() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
}
