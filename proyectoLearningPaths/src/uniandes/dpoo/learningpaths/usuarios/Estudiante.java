package uniandes.dpoo.learningpaths.usuarios;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;

public class Estudiante extends Usuario {

    public Estudiante(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña) {
        super(usuarioID, nombreUsuario, nombre, apellido, contraseña, "Estudiante");
    }

    public void crearResenia(Scanner scanner, PersistenciaLearningPaths persistenciaLearningPaths) {
        System.out.print("Ingrese el título del Learning Path para la reseña: ");
        String titulo = scanner.nextLine();
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            System.out.print("Ingrese la reseña: ");
            String resenia = scanner.nextLine();
            learningPath.agregarResenia(resenia);
            System.out.println("Reseña creada y guardada exitosamente.");
        } else {
            System.out.println("Learning Path no encontrado.");
        }
    }
}
