package uniandes.isis2304.bancandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOperacionDepositosInversion {
	
	private PersistenciaBancandes pp;
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	public  SQLOperacionDepositosInversion (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public List<Object[]> darOperacionDepositosInversionPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionNoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA NOT BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionNoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionNoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionDepositosInversionNoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionDepositosInversion() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}

}
