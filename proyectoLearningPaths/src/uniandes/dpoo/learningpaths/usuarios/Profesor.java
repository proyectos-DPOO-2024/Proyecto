package uniandes.dpoo.learningpaths.usuarios;
import java.util.LinkedList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.CatalogoLearningPaths;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadEncuesta;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadEvaluacion;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadQuiz;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadRevisionRecurso;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadTarea;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.CatalogoActividades;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;

public class Profesor extends Usuario {

	private List<LearningPath> learnignPathsCreados;
	private CatalogoActividades catalogoActividades;
	private CatalogoLearningPaths catalogoLearningPaths;

	public Profesor( String nombreUsuario, String nombre, String apellido, String contraseña) {
		super(nombreUsuario, nombre, apellido, contraseña, "Profesor");
		learnignPathsCreados = new LinkedList<LearningPath>();
		this.catalogoActividades = CatalogoActividades.obtenerInstancia();
		this.catalogoLearningPaths = CatalogoLearningPaths.obtenerInstancia();
	}

	public List<LearningPath> getLearnignPathsCreados() {
		return learnignPathsCreados;
	}

	public void setLearnignPathsCreados(List<LearningPath> learnignPathsCreados) {
		this.learnignPathsCreados = learnignPathsCreados;
	}

	public LearningPath crearLearningPath(String titulo, String descripcion, int duracion, String dificultad, double rating, List<Actividad> actividades) {
	    LearningPath nuevoLearning = new LearningPath(titulo, descripcion, duracion, dificultad, 0);
	    this.learnignPathsCreados.add(nuevoLearning);
	    this.catalogoLearningPaths.agregarLearningPath(nuevoLearning);
	    return nuevoLearning;
	}

	public void listarLearningPathsCreados() {
	    for (LearningPath path : learnignPathsCreados) {
	        System.out.println(path.getTitulo());
	    }
	}

	public void verReseñas(LearningPath learningpath) {
	    List<Reseña> reseñas = learningpath.getResenias();
	    for (Reseña reseña : reseñas) {
	        System.out.println(reseña.getReseñaText());
	    }
	}

	public Actividad crearActividad (String tipo, String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion, String tiporecurso) {
		Actividad actividad = null;

		switch(tipo.toLowerCase()) {
		case "quiz":
			actividad = new ActividadQuiz(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
			break;
		case "encuesta":
			actividad = new ActividadEncuesta(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
			break;
		case "evaluacion":
			actividad = new ActividadEvaluacion(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
			break;
		case "revision recurso":
			actividad = new ActividadRevisionRecurso(titulo, descripcion, objetivo, nivelDificultad, duracion, tiporecurso, calificacion);
			break;
		case "tarea":
			actividad = new ActividadTarea(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion); 
			break;
		}
		this.catalogoActividades.agregarActividad(actividad);
		return actividad;
	}

	public void agregarActLearningPath (int idlearningpath ,int idactividad) {

		for (LearningPath learning : this.learnignPathsCreados) {

			if(learning.getLearningpathID() == idlearningpath) {
				for(Actividad actividad : catalogoActividades.getActividades()) {
					if(actividad.getActividadID() == idactividad) {
						learning.agregarActividad(actividad);;
					}
				}
			}
		}

	}


	public void calificarExamen(Actividad evaluacion) {
		boolean enviado = evaluacion.getCompletada();
		evaluacion.calificar();
	}
	
	public void calificarTarea(Actividad tarea) {
		boolean enviado = tarea.getCompletada();
		tarea.calificar();
	}
}
