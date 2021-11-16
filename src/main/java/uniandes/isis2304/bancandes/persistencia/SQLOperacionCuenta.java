

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
}
	
		