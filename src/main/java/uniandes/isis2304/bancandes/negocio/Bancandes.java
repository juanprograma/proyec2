package uniandes.isis2304.bancandes.negocio;

import uniandes.isis2304.bancandes.persistencia.PersistenciaBancandes;

import uniandes.isis2304.bancandes.negocio.Prestamo;

import java.sql.SQLException;
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
	        
			long  operacion = pb.crearOperacionCuenta(idUsuario, valor, operacionespunto, tipoOperacion, cuentaOrigen, cuentaDestino, idCuenta,tipo);
				
			log.info ("CrearTransaccion cuenta: " + idUsuario); 
			return operacion;
		 
	
		 
	 }
	 



	 public long crearOperacionPrestamo(Timestamp diaPago, long interes, long saldoPendiente, long operacionesPunto,  String tipo, long idprestamo, long idUsuario, int valor , long cuentasOficina, long valorCuotaMinima, long numeroCuotas, long idCliente, String tipoPrestamo,long cuentaOrigen)

	 {

		 log.info ("Crear transaccion : " + idCliente);

		 long  prestamo = pb.crearOperacionPrestamo( diaPago,  interes,  saldoPendiente,  operacionesPunto, tipo,  idprestamo,  idUsuario,  valor ,  cuentasOficina, valorCuotaMinima,  numeroCuotas, idCliente, tipoPrestamo, cuentaOrigen);
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
	 
	 
	 
	 
	 public List<VOPrestamo> darListaPrestamos (long id)
		{
	        log.info ("Dar información de un prestamo por id: " + id);
	        List<VOPrestamo> prestamos = new LinkedList<VOPrestamo> ();
	        for (Prestamo tb :  pb.darPrestamoPoridPrestamo(id))
         {
         	prestamos.add(tb);
         }
	        log.info ("Buscando Prestamo por Id:" + id);
	        return prestamos;
		}

	 
	 public List<VOPrestamo> darListaPrestamosPorTipo (String tipo)
		{
	        log.info ("Dar información de un prestamo por tipo: " + tipo);
	        List<VOPrestamo> prestamos = new LinkedList<VOPrestamo> ();
	        for (Prestamo tb :  pb.darPrestamoPorTipo(tipo))
      {
      	prestamos.add(tb);
      }
	        log.info ("Buscando Prestamo por tipo:" + tipo);
	        return prestamos;
		}

	 
	 public List<VOOperacionCuenta> darOperacionesPorTipo (String tipo)
		{
	        log.info ("Dar información de un bebedor por tipo: " + tipo);
	        List<VOOperacionCuenta> operacionescuenta = new LinkedList<VOOperacionCuenta> ();
	        for (OperacionCuenta tb :  pb.darOperacionCuentaPortipo(tipo))
         {
	        	operacionescuenta.add(tb);
         }
	        log.info ("Buscando bebedor por tipo:" + tipo);
	        return operacionescuenta;
		}

	 
	 public List<VOOperacionCuenta> darOperacionesporIdOperacion (long id)
		{
	        log.info ("Dar información de un bebedor por id: " + id);
	        List<VOOperacionCuenta> operacionescuenta = new LinkedList<VOOperacionCuenta> ();
	        for (OperacionCuenta tb :  pb.darOperacionCuentaPoridCuenta(id))
      {
	        	operacionescuenta .add(tb);
      }
	        log.info ("Buscando bebedor por id:" + id);
	        return operacionescuenta ;
		}
	 
	 public List<Object[]> darOperacionAccionesPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesNoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTNoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaNoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionNoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoNoPorRangoDeFecha(primeraFecha, segundaFecha);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesNoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesNoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTNoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTNoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaNoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaNoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionNoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionNoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoNoPorTipo(String tipo) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoNoPorTipo(tipo);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesNoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesNoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTNoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTNoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaNoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaNoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionNoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionNoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoNoMayoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoNoMayoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionAccionesNoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionAccionesNoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCDTNoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCDTNoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionCuentaNoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionCuentaNoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionDepositosInversionNoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionDepositosInversionNoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darOperacionPrestamoNoMenoresA(int valor) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darOperacionPrestamoNoMenoresA(valor);
		 return tuplas;
	 }
	 
	 public List<Object[]> darConsignacionesDeClientesQueTienenPrestamoConMontoMayorA(int monto) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darConsignacionesDeClientesQueTienenPrestamoConMontoMayorA(monto);
		 return tuplas;
	 }
	 
	 public List<Object[]> darConsignacionesDeClientesQueTienenCDTConMontoMayorA(int monto) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darConsignacionesDeClientesQueTienenCDTConMontoMayorA(monto);
		 return tuplas;
	 } 
	 
	 public List<Object[]> darConsignacionesDeClientesQueTienenAccionesConMontoMayorA(int monto) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darConsignacionesDeClientesQueTienenAccionesConMontoMayorA(monto);
		 return tuplas;
	 }
	 
	 public List<Object[]> darConsignacionesDeClientesQueTienenCuentasConMontoMayorA(int monto) throws SQLException{
		 
		 List<Object[]> tuplas = pb.darConsignacionesDeClientesQueTienenCuentasConMontoMayorA(monto);
		 return tuplas;
	 }
	 
	 public List<Object[]> buscarOperacionesEnDosPuntosDeAtencion(long primerPunto, long segundoPunto) throws SQLException{
		 
		 List<Object[]> tuplas = pb.buscarOperacionesEnDosPuntos(primerPunto, segundoPunto);
		 return tuplas;
	 }
	 
}
