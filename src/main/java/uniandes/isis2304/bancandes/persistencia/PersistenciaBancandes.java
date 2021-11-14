package uniandes.isis2304.bancandes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




public class PersistenciaBancandes {
	
	/* ****************************************************************
	* Constantes
	*****************************************************************/
	/**
	* Logger para escribir la traza de la ejecución
	*/
	private static Logger log = Logger.getLogger(PersistenciaBancandes.class.getName());

	/**
	* Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	*/
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	* Atributos
	*****************************************************************/
	/**
	* Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	*/
	private static PersistenciaBancandes instance;

	/**
	* Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	*/
	private PersistenceManagerFactory pmf;

	/**
	* Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	* Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	*/
	private List <String> tablas;

	/**
	* Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	*/

	
	private SQLUtil sqlUtil;

	/**
	* Atributo para el acceso a la tabla ACCION de la base de datos
	*/
	private SQLAccion sqlAccion;

	/**
	* Atributo para el acceso a la tabla CDT de la base de datos
	*/
	private SQLAtiende sqlAtiende;

	/**
	* Atributo para el acceso a la tabla CLIENTE de la base de datos
	*/
	private SQLCajeroATM sqlCajeroATM;

	/**
	* Atributo para el acceso a la tabla CUENTA de la base de datos
	*/
	private SQLCuenta sqlCuenta;

	/**
	* Atributo para el acceso a la tabla EMPLEADO de la base de datos
	*/
	private SQLCDT sqlCdt;

	/**
	* Atributo para el acceso a la tabla OFICINA de la base de datos
	*/
	private SQLClienteAccion sqlClienteAccion;

	/**
	* Atributo para el acceso a la tabla OPERACION BANCARIA de la base de datos
	*/
	private SQLOficina sqlOficina;

	/**
	* Atributo para el acceso a la tabla PRESTAMO de la base de datos
	*/
	private SQLOperacionAcciones sqlOperacionAcciones;

	/**
	* Atributo para el acceso a la tabla PUNTO ATENCION de la base de datos
	*/
	private SQLOperacionCDT sqlOperacionCDT;

	/**
	* Atributo para el acceso a la tabla PUNTO ATENCION PRESENCIAL de la base de datos
	*/
	private SQLOperacionCuenta sqlOperacionCuenta;

	/**
	* Atributo para el acceso a la tabla USUARIO de la base de datos
	*/
	private SQLOperacionDepositosInversion sqlOperacionDepositosInverson;

	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLOperacionPrestamo sqlOperacionPrestamo;
	
	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLPagoNomina sqlPagoNomina;
	
	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLPuntoAtencion sqlPuntoAtencion;
	
	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLTransaccion sqlTransaccion;
	
	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLUsuario sqlUsuario;
	/**
	* Atributo para el acceso a la tabla PAGO de la base de datos
	*/
	private SQLUsuarioRol sqlUsuarioRol;
	
	






	/* ****************************************************************
	* Métodos del MANEJADOR DE PERSISTENCIA
	*****************************************************************/

	/**
	* Constructor privado con valores por defecto - Patrón SINGLETON
	*/
	private PersistenciaBancandes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");
		crearClasesSQL ();

	// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("BancAndes_sequence");
		tablas.add ("USUARIO");
		tablas.add ("ACCION");
		tablas.add ("PUNTO_ATENCION");
		tablas.add ("OFICINA");
		tablas.add ("PUNTO_ATENCION_PRESENCIAL");
		tablas.add ("EMPLEADO");
		tablas.add ("CLIENTE");
		tablas.add ("PRESTAMO");
		tablas.add ("CUENTA");
		tablas.add ("CDT");
		tablas.add ("OPERACION_BANCARIA");
		tablas.add ("PAGO");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaBancandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaBancandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaBancandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes(tableConfig);
		}
		return instance;
	}

	
	
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombresTablas = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombresTablas)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlAccion = new SQLAccion(this);
		sqlAtiende = new SQLAtiende(this);
		sqlCajeroATM = new SQLCajeroATM(this);
		sqlCuenta = new SQLCuenta(this);
		sqlCdt = new SQLCDT(this);
		sqlOficina = new SQLOficina(this);
		sqlClienteAccion = new SQLClienteAccion(this);	
		sqlOperacionAcciones = new SQLOperacionAcciones(this);
		sqlPuntoAtencion = new SQLPuntoAtencion(this);
		sqlPuntoAtencionPresencial = new SQLPuntoAtencionPresencial (this);
		sqlUsuario = new SQLUsuario(this);	
		sqlPago = new SQLPago(this);	
		sqlUtil = new SQLUtil(this);
	}

	
	
	
	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqBancAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaUsuario()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaAccion()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaPuntoAtencion()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaOficina()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaPuntoAtencionPresencial()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaEmpleado()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaCliente ()
	{
		return tablas.get (7);
	}
	
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaPrestamo()
	{
		return tablas.get (8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaCuenta()
	{
		return tablas.get (9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaCdt()
	{
		return tablas.get (10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaOperacionBancaria()
	{
		return tablas.get (11);
	}
	
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PAGO de BancAndes
	 */
	public String darTablaPago()
	{
		return tablas.get (12);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
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

	

}

	
	
	
	
	
	
	