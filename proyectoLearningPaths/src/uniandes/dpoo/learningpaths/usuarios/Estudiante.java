package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;

public class Estudiante extends Usuario {

	private List<String> learningPathsInscritos;

	public Estudiante(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learningPathsInscritos = new LinkedList<String>();
	}
	
	
	
}
