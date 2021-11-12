package uniandes.isis2304.bancandes.negocio;

public class PagoNomina implements VOPagoNomina {
	
	private long idCuentaPJ;
	
	private long idCuentaPN;
	
	private int valorPagar;
	
	private String frecuencia;
	
	public PagoNomina() {
		this.idCuentaPJ=0;
		this.idCuentaPN=0;
		this.valorPagar=0;
		this.frecuencia="";
	}
	
	public PagoNomina(long idCuentaPJ, long idCuentaPN, int valorPagar, String frecuencia) {
		this.idCuentaPJ=idCuentaPJ;
		this.idCuentaPN=idCuentaPN;
		this.valorPagar=valorPagar;
		this.frecuencia=frecuencia;
	}

	@Override
	public long getIdCuentaPJ() {
		return this.idCuentaPJ;
	}

	@Override
	public long getIdCuentaPN() {
		return this.idCuentaPN;
	}

	@Override
	public int getValorPagar() {
		return this.valorPagar;
	}

	@Override
	public String getFrecuencia() {
		return this.frecuencia;
	} 
	
	public void setIdCuentaPJ(long idCuentaPJ) {
		this.idCuentaPJ=idCuentaPJ;
	}
	
	public void setIdCuentaPN(long idCuentaPN) {
		this.idCuentaPN=idCuentaPN;
	}
	
	public void setValorPagar(int valorPagar) {
		this.valorPagar=valorPagar;
	}
	
	public void setFrecuencia(String frecuencia) {
		this.frecuencia=frecuencia;
	}
	


	

}
