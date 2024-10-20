package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;

public class Profesor extends Usuario {

	private List<String> learnignPathsCreados;

	public Profesor(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learnignPathsCreados = new LinkedList<String>();
	}
	
	
}
