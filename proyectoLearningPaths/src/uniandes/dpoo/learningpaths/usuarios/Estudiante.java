package uniandes.dpoo.learningpaths.usuarios;

import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    public void crearResenia(PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaResenia persistenciaResenias) {
    	String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
        	String resenia = JOptionPane.showInputDialog(this, "Ingrese el la reseña:");
        	int calificacion = JOptionPane.showInputDialog(this, "Ingrese la calificacion (de 1 a 5):");

            if (calificacion < 1 || calificacion > 5) {
            	JOptionPane.showMessageDialog("Calificación inválida. Debe estar entre 1 y 5.");
                return;
            }

            Reseña resenia = new Reseña(reseniaText, calificacion);
            learningPath.agregarResenia(resenia);
            persistenciaResenias.guardarReseña(reseniaText);
            JOptionPane.showMessageDialog("Reseña creada y guardada exitosamente.");
        } else {
        	JOptionPane.showMessageDialog("Learning Path no encontrado.");
        }
    }

    public void inscribirLearningPath(PersistenciaLearningPaths persistenciaLearningPaths, String titulo) {
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            this.inscribirLearningPath(learningPath);
            Estudiante estudiante = new estudiante(nombreUsuario, nombre, apellido, contraseña, "Estudiante");
            learningpath.agregarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Learning Path inscrito exitosamente.");
        } else {
        	JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }

    public void marcarLearningPathCompletado(String titulo) {
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            boolean todasCompletadas = learningPath.getActividades().stream()
                .allMatch(Actividad::getCompletada);

            if (todasCompletadas) {
            	JOptionPane.showMessageDialog("Todas las actividades están completadas. Marcando el Learning Path como completado.");
                
            } else {
            	JOptionPane.showMessageDialogn("Cuidado, te faltan actividades por completar.");
            }
        } else {
        	JOptionPane.showMessageDialog("Learning Path no encontrado.");
        }
    }

    public void verActividadesLearningPath(String titulo) {
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            learningPath.mostrarActividades();
        } else {
        	JOptionPane.showInputDialog("Learning Path no encontrado o no estás inscrito en él.");
        }
    }
    
    public void marcarActividadComoCompleta(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths, String titulo) {
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
        	JOptionPane.showInputDialog("Ingrese el nombre de la actividad: ");
            String nombreActividad = scanner.nextLine();
            Actividad actividad = learningPath.getActividades().stream()
                .filter(act -> act.getTitulo().equals(nombreActividad))
                .findFirst()
                .orElse(null);

            if (actividad != null) {
                actividad.marcarCompletada();
                JOptionPane.showInputDialog("Actividad marcada como completa exitosamente.");
            } else {
            	JOptionPane.showInputDialog("Actividad no encontrada.");
            }
        } else {
        	JOptionPane.showInputDialog("Learning Path no encontrado.");
        }
    }
    
    public void mostrarProgresoActividades(String titulo) {
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
                JOptionPane.showInputDialog("Progreso de actividades completadas: %.2f%%\n", porcentajeCompletado);
            } else {
            	JOptionPane.showInputDialog("No hay actividades en este Learning Path.");
            }
        } else {
        	JOptionPane.showInputDialog("Learning Path no encontrado o no estás inscrito en él.");
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
    public void verFeedback() {
    	String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        LearningPath learningPath = this.getLearningPathsInscritos().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
        	String nombreActividad = JOptionPane.showInputDialog(this, "Ingrese el título de la actividada:");
            Actividad actividad = learningPath.getActividades().stream()
                .filter(act -> act.getTitulo().equals(nombreActividad))
                .findFirst()
                .orElse(null);

            if (actividad != null) {
                Float calificacion = actividad.getCalificacion();
                if (calificacion != null) {
                	JOptionPane.showInputDialog("Calificación de la actividad: " + calificacion);
                } else {
                	JOptionPane.showInputDialog("La actividad aún no ha sido calificada.");
                }
            } else {
            	JOptionPane.showInputDialog("Actividad no encontrada.");
            }
        } else {
        	JOptionPane.showInputDialog("Learning Path no encontrado.");
        }
    }
    
}
