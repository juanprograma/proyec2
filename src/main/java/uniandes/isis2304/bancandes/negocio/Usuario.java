package uniandes.isis2304.bancandes.negocio;

public class Usuario implements VOUsuario {
	
	private long numeroDocumento;
	
	private String tipoDocumento;
	
	private String nombre;
	
	private String nacionalidad;
	
	private String direccionFisica;
	
	private String direccionElectronica;
	
	private int telefono;
	
	private String ciudad;
	
	private String departamento;
	
	private int codigoPostal;
	
	private String tipo;
	
	private String login;
	
	private String palabraClave;
	
	public Usuario() {
		this.numeroDocumento=0;
		this.tipoDocumento="";
		this.nombre="";
		this.nacionalidad="";
		this.direccionFisica="";
		this.direccionElectronica="";
		this.telefono=0;
		this.ciudad="";
		this.departamento="";
		this.codigoPostal=0;
		this.tipo="";
		this.login="";
		this.palabraClave="";
	}
	
	public Usuario(long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, String direccionFisica, String direccionElectronica,
			int telefono, String ciudad, String departamento, int codigoPostal, String tipo, String login, String palabraClave){
		this.numeroDocumento=numeroDocumento;
		this.tipoDocumento=tipoDocumento;
		this.nombre=nombre;
		this.nacionalidad=nacionalidad;
		this.direccionFisica=direccionFisica;
		this.direccionElectronica=direccionElectronica;
		this.telefono=telefono;
		this.ciudad=ciudad;
		this.departamento=departamento;
		this.codigoPostal=codigoPostal;
		this.tipo=tipo;
		this.login=login;
		this.palabraClave=palabraClave;
		
	}
	@Override
	public long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	@Override
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	@Override
	public String getDireccionFisica() {
		return this.direccionFisica;
	}

	@Override
	public String getDireccionElectronica() {
		return this.direccionElectronica;
	}

	@Override
	public int getTelefono() {
		return this.telefono;
	}

	@Override
	public String getCiudad() {
		return this.ciudad;
	}

	@Override
	public String getDepartamento() {
		return this.departamento;
	}

	@Override
	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public String getPalabraClave() {
		return this.palabraClave;
	}

	public void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setDireccionFisica(String direccionFisica) {
		this.direccionFisica = direccionFisica;
	}

	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

}
