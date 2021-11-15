package uniandes.isis2304.bancandes.persistencia;

import java.sql.Timestamp;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;



public class SQLCuenta {

private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pb;
	
	public SQLCuenta(PersistenciaBancandes pb) {
		this.pb = pb;
	}
	
	public String verificarCuentaCorporativa(PersistenceManager pm, long idCuentaPJ) {
		
		Query q = pm.newQuery(SQL, "SELECT U.TIPO FROM " + pb.darTablaUsuario() + " U, " + pb.darTablaCuenta() + " C WHERE U.NUMERODOCUMENTO = C.IDCLIENTE AND C.IDCUENTA = ?");
		q.setParameters(idCuentaPJ);
		q.setResultClass(String.class);
		return (String) q.executeUnique();
	}
	
	public long consignarCuenta(PersistenceManager pm, int cantidad, long idCuenta) {
		
		Query q = pm.newQuery(SQL, "UPDATE " + pb.darTablaCuenta() +" SET SALDO = SALDO + ? WHERE IDCUENTA = ?");
		q.setParameters(cantidad, idCuenta);
		return (long) q.executeUnique();
	}
	
	public long cobrarDeCuenta(PersistenceManager pm, int cantidad, long idCuenta) {
		
		Query q = pm.newQuery(SQL, "UPDATE " + pb.darTablaCuenta() +" SET SALDO = SALDO - ? WHERE IDCUENTA = ?");
		q.setParameters(cantidad, idCuenta);
		return (long) q.executeUnique();
	}
	
	public int consultarSaldoCuenta(PersistenceManager pm, long idCuenta) {
		
		Query q = pm.newQuery(SQL, "SELECT SALDO FROM " + pb.darTablaCuenta() + " WHERE IDCUENTA = ?");
		q.setResultClass(Integer.class);
		q.setParameters(idCuenta);
		return (Integer) q.executeUnique();
		
	}
	
	
	public long crearCuenta (PersistenceManager pm, long idCuenta, String activa, Timestamp fecha, long saldo, Timestamp ultimoMovi, 
							 String tipo, long idCliente, long cuentaOficina ) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pb.darTablaCuenta() + 
				"(idCuenta, activa, fechacreacion, saldo, fechaUltimoMov, tipo, idCliente, cuentasOficina) values ( ?, ?, ?, ?, ?,?,?,?)");
        q.setParameters(idCuenta, activa, fecha, saldo, ultimoMovi, 
				 tipo, idCliente, cuentaOficina);
        return (long) q.executeUnique();
	}
	
	
	
	public long cerrarCuenta(PersistenceManager pm, long idCuenta) {
		

		Query q = pm.newQuery(SQL, "UPDATE " + pb.darTablaCuenta() +" SET ACTIVA = 'N', SALDO = 0 WHERE IDCUENTA = ?");

		q.setParameters(idCuenta);
		return (long) q.executeUnique();
	}
	
	
	public long retirarCuenta(PersistenceManager pm, int cantidad, long idCuenta) {
		
		Query q = pm.newQuery(SQL, "UPDATE " + pb.darTablaCuenta() +" SET SALDO = SALDO - ? WHERE IDCUENTA = ?");
		q.setParameters(cantidad, idCuenta);
		return (long) q.executeUnique();
	}
	
	
}


