package uniandes.isis2304.bancandes.persistencia;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import oracle.sql.TIMESTAMP;
import uniandes.isis2304.bancandes.negocio.OperacionCuenta;
import uniandes.isis2304.bancandes.negocio.OperacionPrestamo;
import uniandes.isis2304.bancandes.negocio.Cuenta;

import uniandes.isis2304.bancandes.negocio.PagoNomina;
import uniandes.isis2304.bancandes.negocio.Transaccion;

import uniandes.isis2304.bancandes.negocio.Prestamo;


import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class PersistenciaBancandes {
	
	private static Logger log = Logger.getLogger(PersistenciaBancandes.class.getName());
	
	public final static String SQL = "javax.jdo.query.SQL";
	
	private static PersistenciaBancandes instance;
	
	private PersistenceManagerFactory pmf;
	
	private List <String> tablas;
	
	private SQLAccion sqlAccion;
	
	private SQLAtiende sqlAtiende;
	
	private SQLCajeroATM sqlCajeroATM;
	
	private SQLCDT sqlCDT;
	
	private SQLClienteAccion sqlClienteAccion;
	
	private SQLCuenta sqlCuenta;
	
	private SQLOficina sqlOficina;
	
	private SQLOperacionAcciones sqlOperacionAcciones;
	
	private SQLOperacionCDT sqlOperacionCDT;
	
	private SQLOperacionCuenta sqlOperacionCuenta;
	
	private SQLOperacionDepositosInversion sqlOperacionDepositosInversion;
	
	private SQLOperacionPrestamo sqlOperacionPrestamo;
	
	private SQLPrestamo  sqlPrestamo;
	
	private SQLPagoNomina sqlPagoNomina;
	
	private SQLPuntoAtencion sqlPuntoAtencion;
	
	private SQLTransaccion sqlTransaccion;
	
	private SQLUsuario sqlUsuario;
	
	private SQLUsuarioRol sqlUsuarioRol;
	
	private SQLUtil sqlUtil;
	
	private PersistenciaBancandes() {
		
		pmf = JDOHelper.getPersistenceManagerFactory("Bancandes");
		crearClasesSQL();
		
		tablas = new LinkedList<String>();
		tablas.add("bancandes_sequence");
		tablas.add("ACCION");
		tablas.add("ATIENDE");
		tablas.add("CAJEROATM");
		tablas.add("CDT");
		tablas.add("CLIENTEACCION");
		tablas.add("CUENTA");
		tablas.add("OFICINA");
		tablas.add("OPERACIONACCIONES");
		tablas.add("OPERACIONCDT");
		tablas.add("OPERACIONCUENTA");
		tablas.add("OPERACIONDEPOSITOSINVERSION");
		tablas.add("OPERACIONPRESTAMO");
		tablas.add("PAGONOMINA");
		tablas.add("PRESTAMO");
		tablas.add("PUNTOATENCION");
		tablas.add("TRANSACCION");
		tablas.add("USUARIO");
		tablas.add("USUARIOROL");		
	}
	
	private PersistenciaBancandes(JsonObject tableConfig) {
		
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
	
	public static PersistenciaBancandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes();
		}
		return instance;
	}
	
	public static PersistenciaBancandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes(tableConfig);
		}
		return instance;
	}
	
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	private void crearClasesSQL() {
		
		sqlAccion= new SQLAccion(this);
		sqlAtiende = new SQLAtiende(this);
		sqlCajeroATM = new SQLCajeroATM(this);
		sqlCDT = new SQLCDT(this);
		sqlClienteAccion = new SQLClienteAccion(this);
		sqlCuenta = new SQLCuenta(this);
		sqlOficina = new SQLOficina(this);
		sqlOperacionAcciones = new SQLOperacionAcciones(this);
		sqlOperacionCDT = new SQLOperacionCDT(this);
		sqlOperacionCuenta = new SQLOperacionCuenta(this);
		sqlOperacionDepositosInversion = new SQLOperacionDepositosInversion(this);
		sqlOperacionPrestamo = new SQLOperacionPrestamo(this);
		sqlPagoNomina = new SQLPagoNomina(this);
		sqlPuntoAtencion = new SQLPuntoAtencion(this);
		sqlTransaccion = new SQLTransaccion(this);
		sqlUsuario = new SQLUsuario(this);
		sqlUsuarioRol = new SQLUsuarioRol(this);
		sqlUtil = new SQLUtil(this);
	}
	
	public String darSeqBancandes() {
		return tablas.get(0);
	}
	
	public String darTablaAccion() {
		return tablas.get(1);
	}
	
	public String darTablaAtiende() {
		return tablas.get(2);
	}
	
	public String darTablaCajeroATM() {
		return tablas.get(3);
	}
	
	public String darTablaCDT() {
		return tablas.get(4);
	}
	
	public String darTablaClienteAccion() {
		return tablas.get(5);
	}
	
	public String darTablaCuenta() {
		return tablas.get(6);
	}
	
	public String darTablaOficina() {
		return tablas.get(7);
	}
	
	public String darTablaOperacionAcciones(){
		return tablas.get(8);
	}
	
	public String darTablaOperacionCDT() {
		return tablas.get(9);
	}
	
	public String darTablaOperacionCuenta() {
		return tablas.get(10);
	}
	
	public String darTablaOperacionDepositosInversion() {
		return tablas.get(11);
	}
	
	public String darTablaOperacionPrestamo() {
		return tablas.get(12);
	}
	
	public String darTablaPagoNomina() {
		return tablas.get(13);
	}
	
	public String darTablaPrestamo() {
		return tablas.get(14);
	}
	
	public String darTablaPuntoAtencion() {
		return tablas.get(15);
	}
	
	public String darTablaTransaccion() {
		return tablas.get(16);
	}
	
	public String darTablaUsuario() {
		return tablas.get(17);
	}
	
	public String darTablaUsuarioRol() {
		return tablas.get(18);
	}
	
	private long nextval()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        return resp;
    }
	
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	public PagoNomina adicionarPagoNomina(long idCuentaPJ, long idCuentaPN, int valorPagar, String frecuencia) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPagoNomina.adicionarPagoNomina(pm, idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
            tx.commit();

            return new PagoNomina(idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	System.out.println("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarPagoNomina(long idCuentaPJ) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPagoNomina.eliminarPagoNomina(pm, idCuentaPJ);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	System.out.println("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public boolean verificarCuentaCorporativa(long idCuentaPJ) {
		
		if(sqlCuenta.verificarCuentaCorporativa(pmf.getPersistenceManager(), idCuentaPJ).equals("Juridica")) 
			return true;
		
		else 
			return false;
	}
	
	public boolean existenCuentasAsociadasAPN(long idCuentaPN) {
		
		PagoNomina cuentaPN = sqlPagoNomina.existenCuentasAsociadasAPN(pmf.getPersistenceManager(), idCuentaPN);
		
		if(cuentaPN != null)
			return true;

		else
			return false;
			
	}
	
	public List<PagoNomina> listarPagosNominaPorIdCorporativa(long idCuentaPJ){
		
		return sqlPagoNomina.listarPagosNominaPorIdCorporativa(pmf.getPersistenceManager(), idCuentaPJ); 
	}
	
	public boolean consignarCuenta(int cantidad, long idCuenta) {

		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasActualizadas = sqlCuenta.consignarCuenta(pm, cantidad, idCuenta);
            tx.commit();

            return true; 
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	System.out.println("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return false;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public boolean cobrarDeCuenta(int cantidad, long idCuenta) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasActualizadas = sqlCuenta.cobrarDeCuenta(pm, cantidad, idCuenta);
            tx.commit();

            return true; 
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	System.out.println("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return false;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public int consultarSaldoCuenta(long idCuenta) {
		
		int saldo = sqlCuenta.consultarSaldoCuenta(pmf.getPersistenceManager(), idCuenta);
		return saldo;
		
	}
	
	
	
	
		
			

	public long crearOperacionCuenta ( long idUsuario, int valor, long operacionespunto, String tipoOperacion, 
										long cuentaOrigen, long cuentaDestino, long idCuenta, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idtransaccion = nextval ();
            long idOperacion = nextval();
            long idCuenta1 = nextval();
            long insertarTran = 0;
            long insertarOpe = 0;
            long insertarCuen = 0;
            Timestamp fechahora = new Timestamp(System.currentTimeMillis());
            if (tipoOperacion.equals("Abrir"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionespunto);
                insertarOpe = sqlOperacionCuenta.crearOperacionCuenta(pm, idOperacion, tipoOperacion, idtransaccion, idCuenta1, cuentaOrigen, cuentaDestino);
                insertarCuen = sqlCuenta.crearCuenta(pm, idCuenta1, "Y", fechahora, valor, fechahora, tipo , idUsuario, 1);
            	
            }
            
            else if (tipoOperacion.equals("Cerrar"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionespunto);
                insertarOpe = sqlOperacionCuenta.crearOperacionCuenta(pm, idOperacion, tipoOperacion, idtransaccion, idCuenta1, cuentaOrigen, cuentaDestino);
                insertarCuen = sqlCuenta.cerrarCuenta(pm, idCuenta);
            	
            	
            }
            
            else if (tipoOperacion.equals("Consignar"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionespunto);
                insertarOpe = sqlOperacionCuenta.crearOperacionCuenta(pm, idOperacion, tipoOperacion, idtransaccion, idCuenta1, cuentaOrigen, cuentaDestino);
                insertarCuen = sqlCuenta.consignarCuenta(pm, valor, idCuenta);
            	
            	
            }
            
            else if (tipoOperacion.equals("Retirar"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionespunto);
                insertarOpe = sqlOperacionCuenta.crearOperacionCuenta(pm, idOperacion, tipoOperacion, idtransaccion, idCuenta1, cuentaOrigen, cuentaDestino);
                insertarCuen = sqlCuenta.retirarCuenta(pm, valor, idCuenta);
            	
            }
            
          
            tx.commit();
            return insertarTran;

           
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	
	
	
	
	public long crearOperacionPrestamo (Timestamp diaPago, long interes, long saldoPendiente, long operacionesPunto, String tipo, long idprestamo, long idUsuario, int valor , long cuentasOficina, long valorCuotaMinima, long numeroCuotas, long idCliente, String tipoPrestamo,long cuentaOrigen) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idtransaccion = nextval ();
            long idOperacion = nextval();
            long idPrestamo1 = nextval();
            long insertarTran = 0;
            long insertarOpe = 0;
            long insertarPres = 0;
            Timestamp fechahora = new Timestamp(System.currentTimeMillis());
            if (tipo.equals("Pedir"))
            {
            	
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionesPunto);
                insertarOpe = sqlOperacionPrestamo.crearOperacionPrestamo(pm,idOperacion, tipo, idtransaccion, idPrestamo1,cuentaOrigen);
                insertarPres = sqlPrestamo.crearPrestamo(pm,  idPrestamo1,  interes,  saldoPendiente,  diaPago, valorCuotaMinima,  numeroCuotas, tipoPrestamo ,idCliente,cuentasOficina);
            	
            }
            
            
            
            else if (tipo.equals("Pagar"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionesPunto);
                insertarOpe = sqlOperacionPrestamo.crearOperacionPrestamo(pm,idOperacion, tipo, idtransaccion, idprestamo, cuentaOrigen);
                insertarPres = sqlPrestamo.consignarPrestamo(pm, valor, idprestamo);
    
            	
            }
            
           
            
          
            tx.commit();
            return insertarTran;

           
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

	}
	
	
	public long cerrarCuenta(long idCuenta) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCuenta.cerrarCuenta(pm, idCuenta);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	System.out.println("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Prestamo> darPrestamoPorTipo (String tipo)
	{
		return sqlPrestamo.darPrestamoPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public List<Prestamo> darPrestamoPoridPrestamo (long idprestamo)
	{
		return sqlPrestamo.darPrestamoPoridPrestamo (pmf.getPersistenceManager(), idprestamo);
	}
	public List<OperacionCuenta> darOperacionCuentaPortipo (String tipo)
	{
		return sqlOperacionCuenta.darOperacionCuentaPortipo (pmf.getPersistenceManager(), tipo);
	}
	
	public List<OperacionCuenta> darOperacionCuentaPoridCuenta (long idcuenta)
	{
		return sqlOperacionCuenta.darOperacionCuentaPoridCuenta(pmf.getPersistenceManager(), idcuenta);
	}
	
	public List<Object[]> darOperacionAccionesPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionAccionesNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesNoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTNoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaNoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionNoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoNoPorRangoDeFecha(String primeraFecha, String segundaFecha) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoNoPorRangoDeFecha(pmf.getPersistenceManager(), primeraFecha, segundaFecha);
		
		for ( Object[] tupla : tuplas) {
	
			String tipo = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipo;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	
	public List<Object[]> darOperacionAccionesPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionAccionesNoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesNoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTNoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTNoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaNoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaNoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionNoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionNoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoNoPorTipo(String tipo) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoNoPorTipo(pmf.getPersistenceManager(), tipo);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	
	public List<Object[]> darOperacionAccionesMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionAccionesNoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesNoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTNoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTNoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaNoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaNoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionNoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionNoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoNoMayoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoNoMayoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionAccionesMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionAccionesNoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionAcciones.darOperacionAccionesNoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCDTNoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCDT.darOperacionCDTNoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionCuentaNoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darOperacionCuentaNoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionDepositosInversionNoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionDepositosInversion.darOperacionDepositosInversionNoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darOperacionPrestamoNoMenoresA(int valorPar) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionPrestamo.darOperacionPrestamoNoMenoresA(pmf.getPersistenceManager(), valorPar);
		
		for ( Object[] tupla : tuplas) {
	
			String tipoOperacion = (String) tupla[0];
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[1];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			int valor = ((BigDecimal) tupla[2]).intValue ();
			
			Object[] operacion = new Object[3];
			operacion[0] = tipoOperacion;
			operacion[1] = fechahora;
			operacion[2] = valor;
			
			respuesta.add(operacion);
		}
		return respuesta;
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenPrestamoConMontoMayorA(int monto) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darConsignacionesDeClientesQueTienenPrestamoConMontoMayorA(pmf.getPersistenceManager(), monto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoOperacion = (String) tupla[0];
			int valor = ((BigDecimal) tupla[1]).intValue ();
			long idCuenta = ((BigDecimal) tupla[2]).longValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[3];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] consignacion = new Object[4];
			consignacion[0] = tipoOperacion;
			consignacion[1] = valor;
			consignacion[2] = idCuenta;
			consignacion[3] = fechahora;
			
			respuesta.add(consignacion);
			
		}
		
		return respuesta;	
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenCDTConMontoMayorA(int monto) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darConsignacionesDeClientesQueTienenCDTConMontoMayorA(pmf.getPersistenceManager(), monto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoOperacion = (String) tupla[0];
			int valor = ((BigDecimal) tupla[1]).intValue ();
			long idCuenta = ((BigDecimal) tupla[2]).longValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[3];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] consignacion = new Object[4];
			consignacion[0] = tipoOperacion;
			consignacion[1] = valor;
			consignacion[2] = idCuenta;
			consignacion[3] = fechahora;
			
			respuesta.add(consignacion);
			
		}
		
		return respuesta;	
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenAccionesConMontoMayorA(int monto) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darConsignacionesDeClientesQueTienenAccionesConMontoMayorA(pmf.getPersistenceManager(), monto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoOperacion = (String) tupla[0];
			int valor = ((BigDecimal) tupla[1]).intValue ();
			long idCuenta = ((BigDecimal) tupla[2]).longValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[3];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] consignacion = new Object[4];
			consignacion[0] = tipoOperacion;
			consignacion[1] = valor;
			consignacion[2] = idCuenta;
			consignacion[3] = fechahora;
			
			respuesta.add(consignacion);
			
		}
		
		return respuesta;	
	}
	
	public List<Object[]> darConsignacionesDeClientesQueTienenCuentasConMontoMayorA(int monto) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlOperacionCuenta.darConsignacionesDeClientesQueTienenCuentasConMontoMayorA(pmf.getPersistenceManager(), monto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoOperacion = (String) tupla[0];
			int valor = ((BigDecimal) tupla[1]).intValue ();
			long idCuenta = ((BigDecimal) tupla[2]).longValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[3];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] consignacion = new Object[4];
			consignacion[0] = tipoOperacion;
			consignacion[1] = valor;
			consignacion[2] = idCuenta;
			consignacion[3] = fechahora;
			
			respuesta.add(consignacion);
			
		}
		
		return respuesta;	
	}
	
	public List<Object[]> buscarOperacionesEnDosPuntos(long primerPunto, long segundoPunto) throws SQLException{
		
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object[]> tuplas = sqlPuntoAtencion.buscarOperacionesEnAccionesEnDosPuntosAtencion(pmf.getPersistenceManager(), primerPunto, segundoPunto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoDocumento = (String) tupla[0];
			long numeroDocumento = ((BigDecimal) tupla[1]).longValue();
			String nombre = (String) tupla[2];
			String tipoDePersona = (String) tupla[3];
			String direccionElectronica = (String) tupla[4];
			long idPuntoAtencion = ((BigDecimal) tupla[5]).longValue();
			String tipo = (String) tupla[6];
			String tipoOperacion = "Tipo de operacion en acciones: " + tipo;
			int valor = ((BigDecimal) tupla[7]).intValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[8];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] operacion = new Object[9];
			operacion[0] = tipoDocumento;
			operacion[1] = numeroDocumento;
			operacion[2] = nombre;
			operacion[3] = tipoDePersona;
			operacion[4] = direccionElectronica;
			operacion[5] = idPuntoAtencion;
			operacion[6] = tipoOperacion;
			operacion[7] = valor;
			operacion[8] = fechahora;
			
			respuesta.add(operacion);
		}
		
		tuplas = sqlPuntoAtencion.buscarOperacionesEnCDTEnDosPuntosAtencion(pmf.getPersistenceManager(), primerPunto, segundoPunto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoDocumento = (String) tupla[0];
			long numeroDocumento = ((BigDecimal) tupla[1]).longValue();
			String nombre = (String) tupla[2];
			String tipoDePersona = (String) tupla[3];
			String direccionElectronica = (String) tupla[4];
			long idPuntoAtencion = ((BigDecimal) tupla[5]).longValue();
			String tipo = (String) tupla[6];
			String tipoOperacion = "Tipo de operacion en CDT: " + tipo;
			int valor = ((BigDecimal) tupla[7]).intValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[8];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] operacion = new Object[9];
			operacion[0] = tipoDocumento;
			operacion[1] = numeroDocumento;
			operacion[2] = nombre;
			operacion[3] = tipoDePersona;
			operacion[4] = direccionElectronica;
			operacion[5] = idPuntoAtencion;
			operacion[6] = tipoOperacion;
			operacion[7] = valor;
			operacion[8] = fechahora;
			
			respuesta.add(operacion);
		}
		
		tuplas = sqlPuntoAtencion.buscarOperacionesEnCuentaEnDosPuntosAtencion(pmf.getPersistenceManager(), primerPunto, segundoPunto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoDocumento = (String) tupla[0];
			long numeroDocumento = ((BigDecimal) tupla[1]).longValue();
			String nombre = (String) tupla[2];
			String tipoDePersona = (String) tupla[3];
			String direccionElectronica = (String) tupla[4];
			long idPuntoAtencion = ((BigDecimal) tupla[5]).longValue();
			String tipo = (String) tupla[6];
			String tipoOperacion = "Tipo de operacion en cuentas: " + tipo;
			int valor = ((BigDecimal) tupla[7]).intValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[8];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] operacion = new Object[9];
			operacion[0] = tipoDocumento;
			operacion[1] = numeroDocumento;
			operacion[2] = nombre;
			operacion[3] = tipoDePersona;
			operacion[4] = direccionElectronica;
			operacion[5] = idPuntoAtencion;
			operacion[6] = tipoOperacion;
			operacion[7] = valor;
			operacion[8] = fechahora;
			
			respuesta.add(operacion);
		}
		
		tuplas = sqlPuntoAtencion.buscarOperacionesEnDepositosDeInversionEnDosPuntosAtencion(pmf.getPersistenceManager(), primerPunto, segundoPunto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoDocumento = (String) tupla[0];
			long numeroDocumento = ((BigDecimal) tupla[1]).longValue();
			String nombre = (String) tupla[2];
			String tipoDePersona = (String) tupla[3];
			String direccionElectronica = (String) tupla[4];
			long idPuntoAtencion = ((BigDecimal) tupla[5]).longValue();
			String tipo = (String) tupla[6];
			String tipoOperacion = "Tipo de operacion en depositos de inversion: " + tipo;
			int valor = ((BigDecimal) tupla[7]).intValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[8];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] operacion = new Object[9];
			operacion[0] = tipoDocumento;
			operacion[1] = numeroDocumento;
			operacion[2] = nombre;
			operacion[3] = tipoDePersona;
			operacion[4] = direccionElectronica;
			operacion[5] = idPuntoAtencion;
			operacion[6] = tipoOperacion;
			operacion[7] = valor;
			operacion[8] = fechahora;
			
			respuesta.add(operacion);
		}
		
		tuplas = sqlPuntoAtencion.buscarOperacionesEnPrestamoEnDosPuntosAtencion(pmf.getPersistenceManager(), primerPunto, segundoPunto);
		
		for ( Object[] tupla : tuplas) {
			
			String tipoDocumento = (String) tupla[0];
			long numeroDocumento = ((BigDecimal) tupla[1]).longValue();
			String nombre = (String) tupla[2];
			String tipoDePersona = (String) tupla[3];
			String direccionElectronica = (String) tupla[4];
			long idPuntoAtencion = ((BigDecimal) tupla[5]).longValue();
			String tipo = (String) tupla[6];
			String tipoOperacion = "Tipo de operacion en prestamos: " + tipo;
			int valor = ((BigDecimal) tupla[7]).intValue();
			oracle.sql.TIMESTAMP fechahoraoracle = (TIMESTAMP) tupla[8];
			Timestamp fechahora = (Timestamp) fechahoraoracle.timestampValue();
			
			Object[] operacion = new Object[9];
			operacion[0] = tipoDocumento;
			operacion[1] = numeroDocumento;
			operacion[2] = nombre;
			operacion[3] = tipoDePersona;
			operacion[4] = direccionElectronica;
			operacion[5] = idPuntoAtencion;
			operacion[6] = tipoOperacion;
			operacion[7] = valor;
			operacion[8] = fechahora;
			
			respuesta.add(operacion);
		}
		
		return respuesta;
				
	}
 
	}

	




	

	



	
	
	
	
	
	
	