

package uniandes.isis2304.bancandes.persistencia;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
}
	
		