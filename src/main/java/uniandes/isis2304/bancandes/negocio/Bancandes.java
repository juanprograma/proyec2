package uniandes.isis2304.bancandes.negocio;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;



public class Bancandes {
	/* ****************************************************************
	  *    Constantes
	  *****************************************************************/
	 /**
	  * Logger para escribir la traza de la ejecución
	  */
	 private static Logger log = Logger.getLogger(Bancandes.class.getName());
	 
	 /* ****************************************************************
	  *    Atributos
	  *****************************************************************/
	 /**
	  * El manejador de persistencia
	  */
	 private PersistenciaBancandes pb;
	 
	 /* ****************************************************************
	  *    Métodos
	  *****************************************************************/
	 /**
	  * El constructor por defecto
	  */
	 public Bancandes ()
	 {
	  pb = PersistenciaBancandes.getInstance ();
	 }
	 
	 /**
	  * El constructor qye recibe los nombres de las tablas en tableConfig
	  * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	  */
	 public Bancandes (JsonObject tableConfig)
	 {
	  pb = PersistenciaBancandes.getInstance (tableConfig);
	 }
	 
	 /**
	  * Cierra la conexión con la base de datos (Unidad de persistencia)
	  */
	 public void cerrarUnidadPersistencia ()
	 {
	  pb.cerrarUnidadPersistencia ();
	 }
	 
	 public PagoNomina adicionarPagoNomina(long idCuentaPJ, long idCuentaPN, int valorPagar, String frecuencia) throws Exception {
		 
		 	PagoNomina resp = null;
		 	if(!pb.verificarCuentaCorporativa(idCuentaPJ)) {
		 		throw new Exception("La cuenta que ingres� no es corporativa.");
		 	}
		 	else if (pb.existenCuentasAsociadasAPN(idCuentaPN)) {
		 		throw new Exception("La cuenta de persona natural ya tiene asociada una cuenta corporativa");
		 	}
		 	else {
		 		resp = pb.adicionarPagoNomina(idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
		 	}
		 	
		 	return resp;
	 }
	 
	 

}
