package uniandes.dpoo.learningpaths.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.usuarios.Estudiante;
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
        scanner.nextLine(); // Consume newline
        
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
        // Obtener el título del LearningPath y la actividad del usuario
        System.out.print("Ingrese el título del Learning Path: ");
        String tituloLearningPath = scanner.nextLine();
        System.out.print("Ingrese el título de la actividad a agregar: ");
        String tituloActividad = scanner.nextLine();

        // Llamar al método agregarActLearningPath de Profesor
        profe.agregarActLearningPath(tituloLearningPath, tituloActividad);
    }

    public static void editarLearningPath(Profesor profe, Scanner scanner) {
        System.out.print("Ingrese el título del Learning Path que desea editar: ");
        String tituloLearningPath = scanner.nextLine();
        System.out.print("Ingrese el nuevo título del Learning Path: ");
        String nuevoTitulo = scanner.nextLine();
        System.out.print("Ingrese la nueva descripción del Learning Path: ");
        String nuevaDescripcion = scanner.nextLine();
        System.out.print("Ingrese la nueva duración del Learning Path (en horas): ");
        int nuevaDuracion = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Ingrese el nuevo nivel de dificultad del Learning Path: ");
        String nuevaDificultad = scanner.nextLine();
        System.out.print("Ingrese el nuevo rating del Learning Path: ");
        double nuevoRating = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Llama al método editarLearningPath del profesor
        profe.editarLearningPath(tituloLearningPath, nuevoTitulo, nuevaDescripcion, nuevaDuracion, nuevaDificultad, nuevoRating);
    } 
    
    public static void calificarActividadesEstudiante(Scanner scanner, Profesor profesor, PersistenciaUsuarios persistenciaUsuarios, PersistenciaLearningPaths persistenciaLearningPaths) {
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
                .filter(lp -> lp.getTitulo().equals(tituloLearningPath))
                .findFirst()
                .orElse(null);

            if (learningPath != null) {
                System.out.print("Ingrese el nombre de la actividad: ");
                String nombreActividad = scanner.nextLine();
                Actividad actividad = learningPath.getActividades().stream()
                    .filter(act -> act.getTitulo().equals(nombreActividad))
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

