package uniandes.isis2304.bancandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

import uniandes.isis2304.bancandes.negocio.PagoNomina;

public class SQLPagoNomina {
  private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pb;
	
	public SQLPagoNomina(PersistenciaBancandes pb) {
		this.pb = pb;
	}
	
	public long eliminarPagoNomina(PersistenceManager pm, long idCuentaPJ) {
		 Query q = pm.newQuery(SQL, "DELETE FROM " + pb.darTablaPagoNomina() + " WHERE IDCUENTAPJ = ?");
		 q.setParameters(idCuentaPJ);
		 return (long) q.executeUnique();
	}
	
	public long adicionarPagoNomina(PersistenceManager pm, long idCuentaPJ, long idCuentaPN, int valorPagar, String frecuencia) {
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + pb.darTablaPagoNomina() + "(idcuentapj, idcuentapn, valorpagar, frecuencia) values (?, ?, ?, ?)");
        q.setParameters(idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
		return (long) q.executeUnique();
	}
	
	public PagoNomina existenCuentasAsociadasAPN(PersistenceManager pm, long idCuentaPN) {
		
		 Query q = pm.newQuery(SQL, "SELECT * FROM " + pb.darTablaPagoNomina() + " PN WHERE PN.IDCUENTAPN = ?");
	     q.setResultClass(PagoNomina.class);
	     q.setParameters(idCuentaPN);
		 return (PagoNomina) q.executeUnique();
	}
	
	public List<PagoNomina> listarPagosNominaPorIdCorporativa(PersistenceManager pm, long idCuentaPJ){
		
		 Query q = pm.newQuery(SQL, "SELECT * FROM " + pb.darTablaPagoNomina() + " PN WHERE PN.IDCUENTAPJ = ?");
	     q.setResultClass(PagoNomina.class);
	     q.setParameters(idCuentaPJ);
		 return (List<PagoNomina>) q.executeList();
	}
	
	
}


