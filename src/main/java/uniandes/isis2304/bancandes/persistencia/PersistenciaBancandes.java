package uniandes.isis2304.bancandes.persistencia;

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

	
	
	
	
	
	public long crearOperacionPrestamo (Timestamp diaPago, long interes, long saldoPendiente, long operacionesPunto, String tipo, long idprestamo, long idUsuario, int valor , long cuentasOficina, long valorCuotaMinima, long numeroCuotas, long idCliente, String tipoPrestamo) 
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
                insertarOpe = sqlOperacionPrestamo.crearOperacionPrestamo(pm,idOperacion, tipo, idtransaccion, idPrestamo1);
                insertarPres = sqlPrestamo.crearPrestamo(pm,  idPrestamo1,  interes,  saldoPendiente,  diaPago, valorCuotaMinima,  numeroCuotas, tipoPrestamo ,idCliente,cuentasOficina);
            	
            }
            
            
            
            else if (tipo.equals("Pagar"))
            {
            	insertarTran = sqlTransaccion.crearTransaccion(pm, idtransaccion, idUsuario, fechahora, valor, operacionesPunto);
                insertarOpe = sqlOperacionPrestamo.crearOperacionPrestamo(pm,idOperacion, tipo, idtransaccion, idprestamo);
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
	}

	




	

	



	
	
	
	
	
	
	