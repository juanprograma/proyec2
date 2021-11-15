package uniandes.isis2304.bancandes.negocio;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

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
	 
	 public List<PagoNomina> listarPagosNominaPorIdCorporativa(long idCuentaPJ){
		 
		 List<PagoNomina> cuentas =  pb.listarPagosNominaPorIdCorporativa(idCuentaPJ);
		 return cuentas;
	 }
	 
	 public boolean consignarCuenta(int cantidad, long idCuenta) {
		 
		 boolean exitoso = pb.consignarCuenta(cantidad, idCuenta);
		 return exitoso;
	 }
	 
	 public int cobrarDeCuenta(int cantidad, long idCuenta) {
		 
			 pb.cobrarDeCuenta(cantidad, idCuenta);
			 return cantidad;
	 }
	 
	 public int consultarSaldo(long idCuenta) {
		 int saldo = pb.consultarSaldoCuenta(idCuenta);
		 return saldo;
	 }
	 
	 public List<PagoNomina> pagarNomina(long idCuenta) {
		 
		 List<PagoNomina> cuentasAPagar = listarPagosNominaPorIdCorporativa(idCuenta);
		 List<PagoNomina> cuentasNoPagadas = new LinkedList<PagoNomina>();
		 
		 for(int i=0; i < cuentasAPagar.size(); i++) {
			 
			 PagoNomina cuentaActual = cuentasAPagar.get(i);
			 int monto = cuentaActual.getValorPagar();
			 int saldo = consultarSaldo(idCuenta);
			 
			 if(monto>saldo) {
				 cuentasNoPagadas.add(cuentaActual);
			 }
			 
			 else {
				 cobrarDeCuenta(monto, idCuenta);
				 consignarCuenta(monto, cuentaActual.getIdCuentaPN());
			 }
		 }
		 
		 return cuentasNoPagadas;
	 }
	 

	
	 
	 
	 public long crearOperacionCuenta(long idUsuario, int valor, long operacionespunto, String tipoOperacion, 
			 									long cuentaOrigen, long cuentaDestino, long idCuenta, String tipo)
	 {

			log.info ("Crear transaccion cuenta: " + idUsuario);
	        
			long  operacion = pb.crearOperacionCuenta(idUsuario, new Timestamp (System.currentTimeMillis()), valor, operacionespunto, tipoOperacion, cuentaOrigen, cuentaDestino, idCuenta,tipo);
				
			log.info ("CrearTransaccion cuenta: " + idUsuario); 
			return operacion;
		 
	
		 
	 }
	 

	 public long crearOperacionPrestamo(Timestamp diaPago, long interes, long saldoPendiente, long operacionesPunto, Timestamp fechahora,  String tipo, long idprestamo, long idUsuario, int valor , long cuentasOficina, long valorCuotaMinima, long numeroCuotas, long idCliente )
	 {

		 log.info ("Crear transaccion : " + idCliente);

		 long  prestamo = pb.crearOperacionPrestamo( diaPago,  interes,  saldoPendiente,  operacionesPunto,  new Timestamp (System.currentTimeMillis()),   tipo,  idprestamo,  idUsuario,  valor ,  cuentasOficina, valorCuotaMinima,  numeroCuotas, idCliente);
		 log.info ("CrearTransaccion : " + idCliente); 
		 return prestamo;



	 }

	 public long cerrarCuenta(long idCuenta, long idCuentaAlterna) {
		 
		 long resp;
		 if(!pb.verificarCuentaCorporativa(idCuenta)) {
			 resp = pb.cerrarCuenta(idCuenta);
			 return resp;
		 }
		 
		 else {
			 List<PagoNomina> cuentasParaAsociar = listarPagosNominaPorIdCorporativa(idCuenta);
			 
			 pb.eliminarPagoNomina(idCuenta);
			 
			 for(PagoNomina pn: cuentasParaAsociar) {
				 try {
					adicionarPagoNomina(idCuentaAlterna, pn.getIdCuentaPN(), pn.getValorPagar(), pn.getFrecuencia());
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
			 
			 resp = pb.cerrarCuenta(idCuenta);
			 return resp;
		 }
		 
	 }
	 


	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
