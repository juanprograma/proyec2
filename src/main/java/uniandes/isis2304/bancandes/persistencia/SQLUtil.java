package uniandes.isis2304.bancandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUtil {

	private final static String SQL = PersistenciaBancandes.SQL;
	
	private PersistenciaBancandes pp;
	
	public SQLUtil(PersistenciaBancandes pp) {
		this.pp = pp;
	}
	
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqBancandes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
}
