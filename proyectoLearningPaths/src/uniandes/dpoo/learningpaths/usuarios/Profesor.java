package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;

public class Profesor extends Usuario {

	private List<LearningPath> learnignPathsCreados;

	public Profesor(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learnignPathsCreados = new LinkedList<LearningPath>();
	}

	public List<LearningPath> getLearnignPathsCreados() {
		return learnignPathsCreados;
	}

	public void setLearnignPathsCreados(List<LearningPath> learnignPathsCreados) {
		this.learnignPathsCreados = learnignPathsCreados;
	}
	
	public void crearLearningPath(LearningPath path) {
	    learnignPathsCreados.add(path);
	}

	public void listarLearningPathsCreados() {
	    for (LearningPath path : learnignPathsCreados) {
	        System.out.println(path.getTitulo());
	    }
	}
	
	public void verReseñas(Actividad actividad) {
	    List<Reseña> reseñas = actividad.getReseñas();
	    for (Reseña reseña : reseñas) {
	        System.out.println(reseña.getReseñaText());
	    }
	}

	public Actividad clonarActividad(Actividad actividadOriginal) {
	    Actividad copia = new Actividad(actividadOriginal); // Constructor de copia
	    return copia;
	}

}
