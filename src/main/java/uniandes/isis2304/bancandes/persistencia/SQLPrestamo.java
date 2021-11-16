package uniandes.isis2304.bancandes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.negocio.Prestamo;






public class SQLPrestamo {

private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pb;
	
	public SQLPrestamo(PersistenciaBancandes pb) {
		this.pb = pb;
	}

public long crearPrestamo (PersistenceManager pm, long idPrestamo, long interes, long saldoPendiente, Timestamp diaPago, long valorCuotaMinima, long numeroCuotas, 
						 String tipo, long idCliente, long cuentasOficina ) {
	
	Query q = pm.newQuery(SQL, "INSERT INTO " + pb.darTablaPrestamo() + 
			"( idPrestamo,  interes,  saldoPendiente,  diaPago, valorCuotaMinima,  numeroCuotas, tipo,idCliente,cuentasOficina) values ( ?, ?, ?, ?, ?,?,?,?,?)");
    q.setParameters(idPrestamo,  interes,  saldoPendiente,  diaPago, valorCuotaMinima,  numeroCuotas, tipo,idCliente,cuentasOficina);
    return (long) q.executeUnique();
}



public long consignarPrestamo(PersistenceManager pm, int cantidad, long idPrestamo) {
	
	Query q = pm.newQuery(SQL, "UPDATE " + pb.darTablaPrestamo() +" SET SALDO = SALDO - ? WHERE IDPRESTAMO = ?");
	q.setParameters(cantidad, idPrestamo);
	return (long) q.executeUnique();
	
	
}
public List<Prestamo> darPrestamoPoridPrestamo (PersistenceManager pm, long idprestamo) 
{
	Query q = pm.newQuery(SQL, "SELECT * FROM " + pb.darTablaPrestamo () + " WHERE idprestamo = ?");
	q.setResultClass(Prestamo.class);
	q.setParameters(idprestamo);
	return (List<Prestamo>) q.executeList();
}
public List<Prestamo> darPrestamoPorTipo (PersistenceManager pm, String tipo) 
{
	Query q = pm.newQuery(SQL, "SELECT * FROM " + pb.darTablaPrestamo () + " WHERE tipo = ?");
	q.setResultClass(Prestamo.class);
	q.setParameters(tipo);
	return (List<Prestamo>) q.executeList();
}

}