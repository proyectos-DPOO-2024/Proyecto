package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;

public class Estudiante extends Usuario {

	private List<LearningPath> learningPathsInscritos;

	public Estudiante(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learningPathsInscritos = new LinkedList<LearningPath>();
	}

	public List<LearningPath> getLearningPathsInscritos() {
		return learningPathsInscritos;
	}

	public void setLearningPathsInscritos(List<LearningPath> learningPathsInscritos) {
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
	        System.out.println("Progreso: " + path.getProgreso());
	    } else {
	        System.out.println("No estás inscrito en este Learning Path.");
	    }
	}

	public void comenzarActividad(LearningPath path, Actividad actividad) {
	    if (learningPathsInscritos.contains(path)) {
	        // Lógica para permitir al estudiante comenzar una actividad
	        actividad.realizar();
	    } else {
	        System.out.println("Debes estar inscrito en el Learning Path para comenzar una actividad.");
	    }
	}

	public void crearReseña(Actividad actividad, String texto, int calificacion) {
	    if (actividad != null) {
	        Reseña nuevaReseña = new Reseña(texto, calificacion);
	        actividad.agregarReseña(nuevaReseña); // Agregar la reseña a la actividad
	        System.out.println("Reseña creada con éxito para la actividad: " + actividad.getTitulo());
	    } else {
	        System.out.println("Actividad no válida.");
	    }
	}

}
