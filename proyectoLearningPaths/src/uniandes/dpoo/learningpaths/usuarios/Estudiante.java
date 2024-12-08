package uniandes.dpoo.learningpaths.usuarios;

import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaResenia;

public class Estudiante extends Usuario {
	private static final long serialVersionUID = 1L;

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
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            boolean todasCompletadas = learningPath.getActividades().stream()
                .allMatch(Actividad::getCompletada);

            if (todasCompletadas) {
                System.out.println("Todas las actividades están completadas. Marcando el Learning Path como completado.");
                
            } else {
                System.out.println("Cuidado, te faltan actividades por completar.");
            }
        } else {
            System.out.println("Learning Path no encontrado.");
        }
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
    
    public void marcarActividadComoCompleta(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.print("Ingrese el título del Learning Path: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
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
                actividad.marcarCompletada();
                System.out.println("Actividad marcada como completa exitosamente.");
            } else {
                System.out.println("Actividad no encontrada.");
            }
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
    
    public void mostrarProgresoActividades(Scanner scanner) {
        System.out.print("Ingrese el título del Learning Path para ver el progreso de sus actividades: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            List<Actividad> actividades = learningPath.getActividades();
            long totalActividades = actividades.size();
            long actividadesCompletadas = actividades.stream().filter(Actividad::getCompletada).count();

            if (totalActividades > 0) {
                double porcentajeCompletado = (double) actividadesCompletadas / totalActividades * 100;
                System.out.printf("Progreso de actividades completadas: %.2f%%\n", porcentajeCompletado);
            } else {
                System.out.println("No hay actividades en este Learning Path.");
            }
        } else {
            System.out.println("Learning Path no encontrado o no estás inscrito en él.");
        }
    }
    
    public void marcarActividadComoIniciada(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.print("Ingrese el título del Learning Path: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
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
                actividad.marcarComoIniciada();
                System.out.println("Actividad marcada como iniciada exitosamente.");
            } else {
                System.out.println("Actividad no encontrada.");
            }
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
    public void verFeedback(Scanner scanner) {
        System.out.print("Ingrese el título del Learning Path para ver el feedback: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
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
                Float calificacion = actividad.getCalificacion();
                if (calificacion != null) {
                    System.out.println("Calificación de la actividad: " + calificacion);
                } else {
                    System.out.println("La actividad aún no ha sido calificada.");
                }
            } else {
                System.out.println("Actividad no encontrada.");
            }
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
    
}
