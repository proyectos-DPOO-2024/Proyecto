package uniandes.dpoo.learningpaths.usuarios;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;

public class Profesor extends Usuario {
	private static final long serialVersionUID = 1L;

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

	public void agregarActLearningPath(String tituloLearningPath, String tituloActividad) {
        LearningPath learningPath = catalogoLearningPaths.getCatalogoLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equalsIgnoreCase(tituloLearningPath))
            .findFirst()
            .orElse(null);

        Actividad actividad = catalogoActividades.getActividades().stream()
            .filter(act -> act.getTitulo().equalsIgnoreCase(tituloActividad))
            .findFirst()
            .orElse(null);

        if (learningPath != null && actividad != null) {
            learningPath.agregarActividad(actividad);
            System.out.println("Actividad agregada al Learning Path exitosamente.");
        } else {
            System.out.println("Learning Path o Actividad no encontrados.");
        }
    } 
	
	public void editarLearningPath(String tituloLearningPath, String nuevoTitulo, String nuevaDescripcion, int nuevaDuracion, String nuevaDificultad, double nuevoRating) {
        LearningPath learningPath = catalogoLearningPaths.getCatalogoLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equalsIgnoreCase(tituloLearningPath))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            learningPath.setTitulo(nuevoTitulo);
            learningPath.setDescripcion(nuevaDescripcion);
            learningPath.setDuracion(nuevaDuracion);
            learningPath.setNivelDificultad(nuevaDificultad);
            learningPath.setRating(nuevoRating);
            System.out.println("Learning Path editado exitosamente.");
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
	
    public void calificarActividad(Scanner scanner, PersistenciaUsuarios persistenciaUsuarios) {
        System.out.print("Ingrese el nombre de usuario del estudiante: ");
        String nombreUsuario = scanner.nextLine();
        Estudiante estudiante = (Estudiante) persistenciaUsuarios.obtenerUsuarios().stream()
            .filter(u -> u.getNombreUsuario().equals(nombreUsuario) && u.getTipoUsuario().equals("Estudiante"))
            .findFirst()
            .orElse(null);

        if (estudiante != null) {
            System.out.print("Ingrese el título del Learning Path: ");
            String tituloLearningPath = scanner.nextLine();
            LearningPath learningPath = estudiante.getLearningPathsInscritos().stream()
                .filter(lp -> lp.getTitulo().equalsIgnoreCase(tituloLearningPath))
                .findFirst()
                .orElse(null);

            if (learningPath != null) {
                System.out.print("Ingrese el nombre de la actividad: ");
                String nombreActividad = scanner.nextLine();
                Actividad actividad = learningPath.getActividades().stream()
                    .filter(act -> act.getTitulo().equalsIgnoreCase(nombreActividad))
                    .findFirst()
                    .orElse(null);

                if (actividad != null) {
                    System.out.print("Ingrese la calificación de la actividad: ");
                    Float calificacion = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    actividad.setCalificacion(calificacion);
                    System.out.println("Actividad calificada exitosamente.");
                } else {
                    System.out.println("Actividad no encontrada.");
                }
            } else {
                System.out.println("Learning Path no encontrado.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }


}
