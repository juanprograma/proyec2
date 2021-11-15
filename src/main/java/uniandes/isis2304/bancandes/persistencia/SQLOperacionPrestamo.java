package uniandes.isis2304.bancandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

public class SQLOperacionPrestamo {

	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLOperacionPrestamo (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long crearOperacionPrestamo (PersistenceManager pm, long idoperacion, String tipo, long idtransaccion, long idprestamo) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacionCuenta() + 
				"(idoperacion, tipo, idtransaccion, idprestamo) values ( ?, ?, ?, ?)");
        q.setParameters(idoperacion, tipo, idtransaccion, idprestamo);
        return (long) q.executeUnique();
	}
}
	
		