package uniandes.dpoo.learningpaths.usuarios;

public abstract class Usuario {
	
	private String usuarioID;
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private String contraseña;
	public Usuario(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		this.usuarioID = usuarioID;
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contraseña = contraseña;
	}
	public String getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(String usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	

}
