package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaResenia;

public class Estudiante extends Usuario {

    public Estudiante( String nombreUsuario, String nombre, String apellido, String contraseña) {
        super(nombreUsuario, nombre, apellido, contraseña, "Estudiante");
    }

    public void crearResenia(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaResenia persistenciaResenias) {
        System.out.print("Ingrese el título del Learning Path para la reseña: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            System.out.print("Ingrese la reseña: ");
            String reseniaText = scanner.nextLine();
            System.out.print("Ingrese la calificación (1-5): ");
            int calificacion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (calificacion < 1 || calificacion > 5) {
                System.out.println("Calificación inválida. Debe estar entre 1 y 5.");
                return;
            }

            Reseña resenia = new Reseña(reseniaText, calificacion);
            learningPath.agregarResenia(resenia);
            persistenciaResenias.guardarReseña(reseniaText);
            System.out.println("Reseña creada y guardada exitosamente.");
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }

    public void inscribirLearningPath(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.print("Ingrese el título del Learning Path al que desea inscribirse: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            this.inscribirLearningPath(learningPath);
            System.out.println("Inscripción al Learning Path exitosa.");
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }

    public void marcarLearningPathCompletado(Scanner scanner) {
        System.out.print("Ingrese el título del Learning Path que desea marcar como completado: ");
        String titulo = scanner.nextLine();
        this.marcarLearningPathCompletado(titulo);
    }

    public void verActividadesLearningPath(Scanner scanner) {
        System.out.print("Ingrese el título del Learning Path para ver sus actividades: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            learningPath.mostrarActividades();
        } else {
            System.out.println("Learning Path no encontrado o no estás inscrito en él.");
        }
    }
}
