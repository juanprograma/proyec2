package uniandes.isis2304.bancandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

public class SQLUsuario {
	
	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLUsuario(PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long crearUsuario(PersistenceManager pm, long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad,
			String direccionfisica, String direccionelectronica, int telefono, String ciudad, String departamento, int codigoPostal,
			String tipo, String login, String palabraclave) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + 
				"(numerodocumento, tipodocumento, nombre, nacionalidad, direccionfisica, direccionelectronica, telefono, ciudad, departamento, codigopostal, tipo, login, palabraclave) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(numeroDocumento, tipoDocumento, nombre, nacionalidad, direccionfisica, direccionelectronica, telefono, ciudad, departamento, codigoPostal, tipo, login, palabraclave);
        return (long) q.executeUnique();
	}
	
		
	
	

}
