package uniandes.isis2304.bancandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOperacionCDT {
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public  SQLOperacionCDT(PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public List<Object[]> darOperacionCDTPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTNoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA NOT BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTNoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTNoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCDTNoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCDT() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}

}
