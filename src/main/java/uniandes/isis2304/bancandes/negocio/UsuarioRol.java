package uniandes.isis2304.bancandes.negocio;

public class UsuarioRol implements VOUsuarioRol {

	private long idCliente;
	
	private String rol;
	
	public UsuarioRol(){
		this.setIdCliente(0);
		this.setRol("");
	}
	
	public UsuarioRol(long idCliente, String rol) {
		this.setIdCliente(idCliente);
		this.setRol(rol);
	}
	
	
	@Override
	public long getIdCliente() {
		return this.idCliente;
	}

	@Override
	public String getRol() {
		return this.rol;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	

}
