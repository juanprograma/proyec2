



package uniandes.isis2304.bancandes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

public class SQLTransaccion {
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLTransaccion (PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long crearTransaccion (PersistenceManager pm, long idtransaccion, long idusuario, Timestamp fechahora, long valor,
			long operacionespunto ) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTransaccion() + 
				"(idtransaccion, idusuario, fechahora, valor, operacionespunto) values ( ?, ?, ?, ?, ?)");
        q.setParameters(idtransaccion, idusuario, fechahora, valor, operacionespunto);
        return (long) q.executeUnique();
	}
	
		
	
	

}
