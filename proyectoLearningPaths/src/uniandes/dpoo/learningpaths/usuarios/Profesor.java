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
        System.out.print("Ingrese la duración del Learning Path (Semanas): ");
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
        }
    }
}