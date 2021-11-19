/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.bancandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.bancandes.negocio.Bancandes;
import uniandes.isis2304.bancandes.negocio.Clienteaccion;
import uniandes.isis2304.bancandes.negocio.OperacionCuenta;
import uniandes.isis2304.bancandes.negocio.PagoNomina;
import uniandes.isis2304.bancandes.negocio.Prestamo;
import uniandes.isis2304.bancandes.negocio.VOOperacionCuenta;
import uniandes.isis2304.bancandes.negocio.VOPagoNomina;
import uniandes.isis2304.bancandes.negocio.VOPrestamo;


/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazBancandesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazBancandesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private Bancandes bancandes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazBancandesApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		bancandes = new Bancandes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			Requerimientos iteracion 2
	 *****************************************************************/
	/**
	 * Adiciona un tipo de bebida con la información dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no existía
	 */


	/* ****************************************************************
	 * 			Requerimientos iteracion 3
	 *****************************************************************/

	public void registrarPagoNomina() {

		try {
			String idCuentaPJstr = JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta corporativa", "Asociar un pago de n�mina", JOptionPane.QUESTION_MESSAGE);
			String idCuentaPNstr = JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta a la que se le va asignar", "Asociar un pago de n�mina", JOptionPane.QUESTION_MESSAGE);
			String valorPagarstr = JOptionPane.showInputDialog(this, "Ingrese el valor a pagar", "Asociar un pago de n�mina", JOptionPane.QUESTION_MESSAGE);
			String frecuencia = JOptionPane.showInputDialog(this, "Ingrese la frecuencia del pago", "Asociar un pago de n�mina", JOptionPane.QUESTION_MESSAGE);

			if(idCuentaPJstr != null && idCuentaPNstr != null && valorPagarstr != null && frecuencia != null)
			{
				long idCuentaPJ = Long.parseLong(idCuentaPJstr);
				long idCuentaPN = Long.parseLong(idCuentaPNstr);
				int valorPagar = Integer.parseInt(valorPagarstr);

				VOPagoNomina pn = bancandes.adicionarPagoNomina(idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
				if (pn == null) {

					throw new Exception("No se pudo asociar las cuentas");

				}
				String resultado = "En PagoNomina\n\n";
				resultado += "Pago de Nomina asociado exitosamente: " + pn;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

			else {
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			} 		
		}
		catch (Exception e) {
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void pagoNomina() {

		try {
			String idCuentaPJstr = JOptionPane.showInputDialog(this, "Ingrese la cuenta de donde se va cobrar para pagar n�mina.", "Pago de nomina", JOptionPane.QUESTION_MESSAGE);

			if(idCuentaPJstr != null)
			{
				long idCuentaPJ = Long.parseLong(idCuentaPJstr);

				List<PagoNomina> lpn = bancandes.pagarNomina(idCuentaPJ);
				if (lpn.isEmpty()) {

					panelDatos.actualizarInterfaz("Se han pagado todas las cuentas asociadas a la cuenta: " + idCuentaPJ);

				}

				else {
					String resultado = "Estas son las cuentas que quedaron pendientes de pagar: \n";
					for(int i = 0; i < lpn.size(); i++) {
						resultado += "Cuenta: " + lpn.get(i).getIdCuentaPN() + "\n"; 
					}
					panelDatos.actualizarInterfaz(resultado);
				}
			}

			else {
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			} 		
		}
		catch (Exception e) {
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void cerrarCuenta() {

		try 
		{
			String idCuentastr;
			String idCuentaAlternastr;
			long idCuenta;
			long idCuentaAlterna;
			long resp;

			int opcion = JOptionPane.showOptionDialog(this, "�La cuenta que desea cerrar es corporativa?", "Cerrar cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if(opcion == 0) {
				idCuentastr = JOptionPane.showInputDialog(this, "Ingrese la cuenta a cerrar", "Cerrar cuenta", JOptionPane.QUESTION_MESSAGE);
				idCuentaAlternastr = JOptionPane.showInputDialog(this, "Ingrese la cuenta que se va encargar de pagar la nomina.", "Cerrar cuenta", JOptionPane.QUESTION_MESSAGE);

				if (idCuentastr != null && idCuentaAlternastr != null)
				{
					idCuenta = Long.valueOf(idCuentastr);
					idCuentaAlterna = Long.valueOf(idCuentaAlternastr);
					resp = bancandes.cerrarCuenta(idCuenta, idCuentaAlterna);

					String resultado = "En cerrar Cuenta\n\n";
					resultado += resp + " Cuenta(s) cerrada\n";
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else {
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
				}

			}
			else if(opcion == 1) {
				idCuentastr = JOptionPane.showInputDialog(this, "Ingrese la cuenta a cerrar", "Cerrar cuenta", JOptionPane.QUESTION_MESSAGE);
				idCuentaAlternastr = null;
				if(idCuentastr != null && idCuentaAlternastr == null) {

					idCuenta = Long.valueOf(idCuentastr);
					idCuentaAlterna = 0;
					resp = bancandes.cerrarCuenta(idCuenta, 0);

					String resultado = "En cerrar Cuenta\n\n";
					resultado += resp + " Cuenta(s) cerrada\n";
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);

				}
				else {
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
				}
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void crearOperacionCuenta() {

		try {

			String tipoOperacion = JOptionPane.showInputDialog(this, "Indique el tipo de operaci�n que desea registrar", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String idUsuariostr = JOptionPane.showInputDialog(this, "Ingrese el id del usuario", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String valorstr = JOptionPane.showInputDialog(this, "Ingrese el valor (si su tipo de operacion es abrir o cerrar coloque 0)", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String operacionesPuntostr = JOptionPane.showInputDialog(this, "Ingrese el id del punto de atencion", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String cuentaOrigenstr = JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de origen(si su tipo es abrir, cerrar o retirar el id de origen y destino es el mismo.)", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String cuentaDestinostr = JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de destino(si su tipo es abrir, cerrar o retirar el id de origen y destino es el mismo.)", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);
			String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de la cuenta a abrir (en caso que la operacion no sea abrir no ingrese nada)", "Operacion cuenta", JOptionPane.QUESTION_MESSAGE);


			if(tipoOperacion != null)
			{
				long idUsuario = Long.parseLong(idUsuariostr);
				int valor = Integer.parseInt(valorstr);
				long operacionesPunto = Long.parseLong(operacionesPuntostr);
				long cuentaOrigen = Long.parseLong(cuentaOrigenstr); 
				long cuentaDestino = Long.parseLong(cuentaDestinostr);
				long idCuenta = cuentaDestino;

				long oc = bancandes.crearOperacionCuenta(idUsuario, valor, operacionesPunto, tipoOperacion, cuentaOrigen, cuentaDestino, idCuenta, tipo);

				panelDatos.actualizarInterfaz("Se han insertado: " + oc + " transacciones");
			}

			else {
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			} 		
		}
		catch (Exception e) {
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void crearOperacionPrestamo() {

		try {

			String tipoOperacion = JOptionPane.showInputDialog(this, "Indique el tipo de operaci�n de prestamo que desea registrar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);


			if(tipoOperacion != null)
			{
				if(tipoOperacion.equals("Pedir")) {

					String diastr = JOptionPane.showInputDialog(this, "Ingrese el dia de pago limite", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String messtr = JOptionPane.showInputDialog(this, "Indique el mes de pago limite", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String aniostr = JOptionPane.showInputDialog(this, "Indique el a�o de pago limite", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String saldoPendientestr = JOptionPane.showInputDialog(this, "Ingrese el valor del prestamo", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String valorCuotaMinimastr = JOptionPane.showInputDialog(this, "Ingrese el valor de la cuota minima del prestamo", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String tipoPrestamo = JOptionPane.showInputDialog(this, "Ingrese el tipo del prestamo", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String numeroCuotasstr = JOptionPane.showInputDialog(this, "Ingrese le numero de cuotas", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String idClientestr = JOptionPane.showInputDialog(this, "Ingrese el id del cliente que pide el prestamo", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String cuentasOficinastr = JOptionPane.showInputDialog(this, "Indique el id de la oficina", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String operacionesPuntostr = JOptionPane.showInputDialog(this, "Ingrese el id del punto de atencion", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);

					if(diastr != null && messtr != null && aniostr != null && saldoPendientestr != null && valorCuotaMinimastr != null && tipoPrestamo != null && numeroCuotasstr != null 
							&& idClientestr != null && cuentasOficinastr != null && operacionesPuntostr != null)
					{
						//Cuando se va a crear
						int dia = Integer.parseInt(diastr);
						int mes = Integer.parseInt(messtr);
						int anio = Integer.parseInt(aniostr);

						Timestamp diaPago = null;
						diaPago.setDate(dia);
						diaPago.setMonth(mes);
						diaPago.setYear(anio);

						long interes = 0;
						long saldoPendiente = Long.parseLong(saldoPendientestr);
						long operacionesPunto = Long.parseLong(operacionesPuntostr);
						long valorCuotaMinima = Long.parseLong(valorCuotaMinimastr);
						long numeroCuotas = Long.parseLong(numeroCuotasstr); 
						long idCliente = Long.parseLong(idClientestr);
						long cuentasOficina = Long.parseLong(cuentasOficinastr);

						long op = bancandes.crearOperacionPrestamo(diaPago, interes, saldoPendiente, operacionesPunto, tipoOperacion, 0, idCliente, 0, cuentasOficina, valorCuotaMinima, numeroCuotas, idCliente, tipoPrestamo);
					}
					else {
						panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
					}
				}

				else if (tipoOperacion.equals("Pagar")){

					String valorstr = JOptionPane.showInputDialog(this, "Ingrese el valor a abonar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String idPrestamostr = JOptionPane.showInputDialog(this, "Ingrese el id del prestamo a abonar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
					String idCliente = JOptionPane.showInputDialog(this, "Ingrese el id del cliente", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);

					if(valorstr != null && idPrestamostr !=null && idCliente != null) {

						int valor = Integer.parseInt(valorstr);
						long idprestamo = Long.parseLong(idPrestamostr); 
						long idUsuario = Long.parseLong(idCliente);

						long op = bancandes.crearOperacionPrestamo(null, 0, 0, 0, tipoOperacion, idprestamo, idUsuario, valor , 0, 0, 0, idUsuario, null);

					}

					else {
						panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
					}

				}
			}

			else {
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			} 		
		}
		catch (Exception e) {
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */


	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una líea para cada tipo de bebida recibido
	 */


	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecucion";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazBancandesApp.class.getMethod( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazBancandesApp interfaz = new InterfazBancandesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}

	public void prestamoporid()
	{String prestamoid = JOptionPane.showInputDialog(this, "Ingrese id a buscar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
	if(prestamoid != null)	{
		long prestamo = Long.parseLong (prestamoid );
		List<VOPrestamo> Resultado = bancandes.darListaPrestamos(prestamo) ;
		String ans = "";
		for (VOPrestamo cuenta: Resultado) {
			ans += cuenta.toString();
		}
         panelDatos.actualizarInterfaz(ans);}


	}    

	public void consultarPrestamos()
	{String prestamotipo = JOptionPane.showInputDialog(this, "Ingrese tipo a buscar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);

	if(prestamotipo != null) {
		List<VOPrestamo> Resultado = bancandes.darListaPrestamosPorTipo (prestamotipo );
		String ans = "";
		for (VOPrestamo cuenta: Resultado) {
			ans += cuenta.toString();
		}
         panelDatos.actualizarInterfaz(ans);}


else { panelDatos.actualizarInterfaz("Operacion cancelada por el usuario"); }}



	public void consultarOperaciones()
	{String operacionid = JOptionPane.showInputDialog(this, "Ingrese id a buscar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
	long prestamo = Long.parseLong (operacionid );
	if(operacionid != null) {
		List<VOOperacionCuenta> Resultado =	bancandes.darOperacionesporIdOperacion (prestamo);
		
		String ans = "";
		for (VOOperacionCuenta cuenta: Resultado) {
			ans += cuenta.toString();
		}
panelDatos.actualizarInterfaz(ans);}


else { panelDatos.actualizarInterfaz("Operacion cancelada por el usuario"); }





	  }



	public void operacionportipo()
	{String operaciontipo = JOptionPane.showInputDialog(this, "Ingrese tipo a buscar", "Operacion prestamo", JOptionPane.QUESTION_MESSAGE);
	if(operaciontipo  != null) {
		List<VOOperacionCuenta>  Resultado =  bancandes.darOperacionesPorTipo(operaciontipo);


				String ans = "";
				for (VOOperacionCuenta cuenta: Resultado) {
					ans += cuenta.toString();
				}
		panelDatos.actualizarInterfaz(ans);}


	else { panelDatos.actualizarInterfaz("Operacion cancelada por el usuario"); }












	}   }

