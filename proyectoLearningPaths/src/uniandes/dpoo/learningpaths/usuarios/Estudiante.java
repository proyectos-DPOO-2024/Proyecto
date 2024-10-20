package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;

public class Estudiante extends Usuario {

	private List<String> learningPathsInscritos;

	public Estudiante(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learningPathsInscritos = new LinkedList<String>();
	}

	public List<String> getLearningPathsInscritos() {
		return learningPathsInscritos;
	}

	public void setLearningPathsInscritos(List<String> learningPathsInscritos) {
		this.learningPathsInscritos = learningPathsInscritos;
	}
	
	
	public void inscribirLearningPath(LearningPath path) {
	    if (!learningPathsInscritos.contains(path)) {
	        learningPathsInscritos.add(path);
	    }
	}
	
	public void listarLearningPaths() {
	    for (LearningPath path : learningPathsInscritos) {
	        System.out.println(path.getTitulo());
	    }
	}
	
	public void verProgreso(LearningPath path) {
	    if (learningPathsInscritos.contains(path)) {
	        // Lógica para mostrar el progreso del estudiante en el Learning Path
	        System.out.println("Progreso: " + path.getProgreso(this));
	    } else {
	        System.out.println("No estás inscrito en este Learning Path.");
	    }
	}

	public void comenzarActividad(LearningPath path, Actividad actividad) {
	    if (learningPathsInscritos.contains(path)) {
	        // Lógica para permitir al estudiante comenzar una actividad
	        actividad.iniciar();
	    } else {
	        System.out.println("Debes estar inscrito en el Learning Path para comenzar una actividad.");
	    }
	}

}
