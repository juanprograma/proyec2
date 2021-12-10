

package uniandes.isis2304.bancandes.persistencia;



import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.negocio.OperacionCuenta;
import uniandes.isis2304.bancandes.negocio.Prestamo;
import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

public class SQLOperacionCuenta {
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLOperacionCuenta (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long crearOperacionCuenta (PersistenceManager pm, long idoperacion, String tipo, long idtransaccion, long idcuenta,
			long cuentaorigen, long cuentadestinp ) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacionCuenta() + 
				"(idoperacion, tipo, idtransaccion, idcuenta,cuentaorigen,cuentadestinp) values ( ?, ?, ?, ?, ?,?)");
        q.setParameters(idoperacion, tipo, idtransaccion, idcuenta,cuentaorigen,cuentadestinp);
        return (long) q.executeUnique();
	}
	
	public List<OperacionCuenta> darOperacionCuentaPoridCuenta (PersistenceManager pm, long idcuenta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacionCuenta () + " WHERE idcuenta = ?");
		q.setResultClass(Prestamo.class);
		q.setParameters(idcuenta);
		return (List<OperacionCuenta>) q.executeList();
	}
	public List<OperacionCuenta> darOperacionCuentaPortipo (PersistenceManager pm, String tipo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacionCuenta  () + " WHERE tipo = ?");
		q.setResultClass(Prestamo.class);
		q.setParameters(tipo);
		return (List<OperacionCuenta>) q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaNoPorRangoDeFecha(PersistenceManager pm, String primeraFecha, String segundaFecha) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.FECHAHORA NOT BETWEEN ? AND ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(primeraFecha, segundaFecha);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaNoPorTipo(PersistenceManager pm, String tipo) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT O.TIPO = ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(tipo);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaNoMayoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darOperacionCuentaNoMenoresA(PersistenceManager pm, int valor) 
	{
		String sql = "SELECT O.TIPO, T.FECHAHORA, T.VALOR ";
		sql += " FROM ";
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += " WHERE ";
		sql += "O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND NOT T.VALOR < ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(valor);
		return q.executeList();
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenPrestamoConMontoMayorA(PersistenceManager pm, int monto){
		
		String sql = "SELECT O.TIPO, T.VALOR, O.IDCUENTA, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaUsuario() + " U, ";
		sql += pp.darTablaPrestamo() + " P, "; 
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += "WHERE ";
		sql += "U.NUMERODOCUMENTO = P.IDCLIENTE ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND U.NUMERODOCUMENTO = T.IDUSUARIO ";
		sql += "AND O.TIPO = 'Consignar' ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(monto);
		return q.executeList();
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenCDTConMontoMayorA(PersistenceManager pm, int monto){
		
		String sql = "SELECT O.TIPO, T.VALOR, O.IDCUENTA, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaUsuario() + " U, ";
		sql += pp.darTablaCDT() + " C, "; 
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += "WHERE ";
		sql += "U.NUMERODOCUMENTO = C.IDCLIENTE ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND U.NUMERODOCUMENTO = T.IDUSUARIO ";
		sql += "AND O.TIPO = 'Consignar' ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(monto);
		return q.executeList();
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenAccionesConMontoMayorA(PersistenceManager pm, int monto){
		
		String sql = "SELECT O.TIPO, T.VALOR, O.IDCUENTA, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaUsuario() + " U, ";
		sql += pp.darTablaClienteAccion() + " CA, "; 
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += "WHERE ";
		sql += "U.NUMERODOCUMENTO = CA.IDCLIENTE ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND U.NUMERODOCUMENTO = T.IDUSUARIO ";
		sql += "AND O.TIPO = 'Consignar' ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(monto);
		return q.executeList();
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenCuentasConMontoMayorA(PersistenceManager pm, int monto){
		
		String sql = "SELECT O.TIPO, T.VALOR, O.IDCUENTA, T.FECHAHORA ";
		sql += "FROM ";
		sql += pp.darTablaUsuario() + " U, ";
		sql += pp.darTablaCuenta() + " C, "; 
		sql += pp.darTablaOperacionCuenta() + " O, ";
		sql += pp.darTablaTransaccion() + " T ";
		sql += "WHERE ";
		sql += "U.NUMERODOCUMENTO = C.IDCLIENTE ";
		sql += "AND O.IDTRANSACCION = T.IDTRANSACCION ";
		sql += "AND U.NUMERODOCUMENTO = T.IDUSUARIO ";
		sql += "AND O.TIPO = 'Consignar' ";
		sql += "AND T.VALOR > ? ";
		
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(monto);
		return q.executeList();
	}
}
	
		