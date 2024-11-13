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

	public Profesor(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña, CatalogoActividades catalogoActividades, CatalogoLearningPaths catalogolearningpaths) {
		super(usuarioID, nombreUsuario, nombre, apellido, contraseña);
		learnignPathsCreados = new LinkedList<LearningPath>();
		this.catalogoActividades = catalogoActividades;
		this.catalogoLearningPaths = catalogolearningpaths;
	}

	public List<LearningPath> getLearnignPathsCreados() {
		return learnignPathsCreados;
	}

	public void setLearnignPathsCreados(List<LearningPath> learnignPathsCreados) {
		this.learnignPathsCreados = learnignPathsCreados;
	}
	
	public void crearLearningPath(String titulo, String descripcion, int duracion, String dificultad, double rating, List<Actividad> actividades) {
	    LearningPath nuevoLearning = new LearningPath(titulo, descripcion, duracion, dificultad, 0);
	    this.learnignPathsCreados.add(nuevoLearning);
	    this.catalogoLearningPaths.agregarLearningPath(nuevoLearning);
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
	
	public void crearActividad (String tipo, String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion, Float calificacionMin, String tiporecurso) {
		Actividad actividad = null;
		
		switch(tipo.toLowerCase()) {
		case "quiz":
			actividad = new ActividadQuiz(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion, calificacionMin);
			break;
		case "encuesta":
			actividad = new ActividadEncuesta(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
			break;
		case "evaluacion":
			actividad = new ActividadEvaluacion(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion, calificacionMin);
			break;
		case "revision recurso":
			actividad = new ActividadRevisionRecurso(titulo, descripcion, objetivo, nivelDificultad, duracion, tiporecurso, calificacion);
			break;
		case "tarea":
			actividad = new ActividadTarea(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion); 
			break;
		}
		this.catalogoActividades.agregarActividad(actividad);
	}

	public void agregarActLearningPath (int idlearningpath ,int idactividad) {
		
		for (LearningPath learning : this.learnignPathsCreados) {
			
			if(learning.getLearningpathID() == idlearningpath) {
				for(Actividad actividad : catalogoActividades.getActividades()) {
					if(actividad.getActividadID() == idactividad) {
						learning.agregarActividad(actividad);
					}
				}
			}
		}
		
	}
	public void calificarTarea(ActividadTarea tarea) {
		boolean enviada = tarea.getEnviada();
		tarea.calificar(enviada);
	}

	public void calificarExamen(Actividad evaluacion) {
		boolean enviado = evaluacion.getCompletada();
		evaluacion.clificar();
	}
}
