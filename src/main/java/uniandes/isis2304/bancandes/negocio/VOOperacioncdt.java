package uniandes.isis2304.bancandes.negocio;

public interface VOOperacioncdt {
public long getIdoperacion();
public String getTipo ();
public long getIdtransaccion();
public long getIdcuenta ();
@Override
public String toString();
}
