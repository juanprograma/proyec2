package uniandes.isis2304.bancandes.negocio;

import java.util.Date;

public interface VOCuenta {
public long getIdcuenta ();
public String getActiva ();
public Date getFechacreacion ();
public long getSaldo ();
public Date getFechaultimomov();
public String getTipo ();
public long getIdcliente();
public long getCuentasoficina ();

@Override
public String toString();
}
