package uniandes.isis2304.bancandes.persistencia;

public class SQLPagoNomina {
  private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLPagoNomina(PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long adicionarPagoNomina(PersistenceManager pm, long idCuentaPJ, long idCuentaPN, int valorPagar, String frecuencia) {
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPagoNomina() + "(idcuentapj, idcuentapn, valorpagar, frecuencia) values (?, ?, ?, ?)");
        q.setParameters(idCuentaPJ, idCuentaPN, valorPagar, frecuencia);
		return (long) q.executeUnique();
	}
}

}
