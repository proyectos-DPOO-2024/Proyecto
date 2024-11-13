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
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;

public class Profesor extends Usuario {
    public Profesor(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
        super(usuarioID, nombreUsuario, nombre, apellido, contraseña, "Profesor");
    }

    public void crearLearningPath(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
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
        scanner.nextLine(); // Consume newline

        LearningPath learningPath = new LearningPath(titulo, descripcion, duracion, dificultad, rating);
        persistenciaLearningPaths.guardarLearningPath(learningPath);
        System.out.println("Learning Path creado y guardado exitosamente.");
    }

    public void verLearningPaths(PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.println("Learning Paths:");
        for (LearningPath learningPath : persistenciaLearningPaths.obtenerLearningPaths()) {
            System.out.println("- " + learningPath.getTitulo());
            learningPath.mostrarResenias();
            learningPath.mostrarActividades();
        }
    }

    public void crearActividad(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaActividades persistenciaActividades) {
        System.out.print("Ingrese el título del Learning Path al que desea agregar la actividad: ");
        String tituloLP = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(tituloLP))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            System.out.print("Ingrese el tipo de actividad (Tarea/Evaluacion/Quiz): ");
            String tipoActividad = scanner.nextLine().toLowerCase();
            System.out.print("Ingrese el título de la actividad: ");
            String titulo = scanner.nextLine();
            System.out.print("Ingrese la descripción de la actividad: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese el objetivo de la actividad: ");
            String objetivo = scanner.nextLine();
            System.out.print("Ingrese el nivel de dificultad de la actividad: ");
            String nivelDificultad = scanner.nextLine();
            System.out.print("Ingrese la duración de la actividad (en horas): ");
            int duracion = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Ingrese la calificación de la actividad: ");
            Float calificacion = scanner.nextFloat();
            scanner.nextLine(); // Consume newline

            Actividad actividad;
            if (tipoActividad.equals("tarea")) {
                actividad = new ActividadTarea(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
            } else if (tipoActividad.equals("evaluacion")) {
                actividad = new ActividadEvaluacion(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
            } else if (tipoActividad.equals("quiz")) {
                actividad = new ActividadQuiz(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
            } else {
                System.out.println("Tipo de actividad no válido.");
                return;
            }

            learningPath.agregarActividad(actividad);
            persistenciaActividades.guardarActividad(actividad);
            System.out.println("Actividad creada y agregada al Learning Path exitosamente.");
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
}