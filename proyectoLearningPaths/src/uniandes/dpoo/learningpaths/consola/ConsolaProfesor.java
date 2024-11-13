package uniandes.dpoo.learningpaths.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadEvaluacion;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadQuiz;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadTarea;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.usuarios.Profesor;

public class ConsolaProfesor {

	
	public static void crearLearningPath(Profesor profe, Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.print("Ingrese el título del Learning Path: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la descripción del Learning Path: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese la duración del Learning Path (en horas): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Ingrese el nivel de dificultad del Learning Path: ");
        String dificultad = scanner.nextLine();
        System.out.print("Ingrese el rating del Learning Path: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consume newlin
        
        List<Actividad> actividades = new ArrayList<>();

        

        LearningPath learningPath = profe.crearLearningPath(titulo, descripcion, duracion, dificultad, rating, actividades);
        persistenciaLearningPaths.guardarLearningPath(learningPath);
        System.out.println("Learning Path creado y guardado exitosamente.");
    }




    public static void verLearningPaths(PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.println("Learning Paths:");
        for (LearningPath learningPath : persistenciaLearningPaths.obtenerLearningPaths()) {
            System.out.println("- " + learningPath.getTitulo());
            learningPath.mostrarResenias();
            learningPath.mostrarActividades();
        }
    }

    
    public static void crearNuevaActividad(Scanner scanner, Profesor profe) {
        System.out.print("Ingrese el tipo de actividad (quiz, encuesta, evaluacion, revision recurso, tarea): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese el título de la actividad: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese la descripción de la actividad: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el objetivo de la actividad: ");
        String objetivo = scanner.nextLine();

        System.out.print("Ingrese el nivel de dificultad de la actividad: ");
        String nivelDificultad = scanner.nextLine();

        System.out.print("Ingrese la duración de la actividad (en minutos): ");
        int duracion = scanner.nextInt();

        System.out.print("Ingrese la calificación de la actividad: ");
        Float calificacion = scanner.nextFloat();
        scanner.nextLine(); // Consumir el salto de línea

        String tiporecurso = null;
        if (tipo.equalsIgnoreCase("revision recurso")) {
            System.out.print("Ingrese el tipo de recurso para la revisión: ");
            tiporecurso = scanner.nextLine();
        }

        // Llama al método crearActividad del profesor
        profe.crearActividad(tipo, titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion, tiporecurso);
    }
    
    public static void agregarActividadALearningPath(Profesor profe, Scanner scanner) {
        // Obtener el ID del LearningPath y la actividad del usuario
        System.out.print("Ingrese el ID del Learning Path: ");
        int idlearningpath = scanner.nextInt();
        System.out.print("Ingrese el ID de la actividad a agregar: ");
        int idactividad = scanner.nextInt();

        // Llamar al método agregarActLearningPath de Profesor
        profe.agregarActLearningPath(idlearningpath, idactividad);

    }
    
}

