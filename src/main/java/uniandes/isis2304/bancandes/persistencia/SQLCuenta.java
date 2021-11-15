package uniandes.isis2304.bancandes.persistencia;

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
}
