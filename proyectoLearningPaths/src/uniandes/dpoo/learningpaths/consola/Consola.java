package uniandes.dpoo.learningpaths.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.usuarios.Profesor;
import uniandes.dpoo.learningpaths.usuarios.Usuario;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaResenia;

public class Consola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
        PersistenciaResenia persistenciaResenias = new PersistenciaResenia();
        PersistenciaLearningPaths persistenciaLearningPaths = new PersistenciaLearningPaths();
        PersistenciaActividades persistenciaActividades = new PersistenciaActividades();
        
        while (true) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear usuario");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (opcion == 1) {
                iniciarSesion(scanner, persistenciaUsuarios, persistenciaResenias, persistenciaLearningPaths, persistenciaActividades);
            } else if (opcion == 2) {
                crearUsuario(scanner, persistenciaUsuarios);
            } else if (opcion == 3) {
                System.out.println("Saliendo...");
                break;
            }
        }
        scanner.close();
    }

    private static void crearUsuario(Scanner scanner, PersistenciaUsuarios persistencia) {
        System.out.print("Tipo de usuario (Estudiante/Profesor): ");
        String tipoUsuario = scanner.nextLine().toLowerCase();

        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (tipoUsuario.equals("estudiante")) {
            Estudiante estudiante = new Estudiante(nombreUsuario, nombre, apellido, contraseña);
            persistencia.guardarUsuario(estudiante);
            System.out.println("Estudiante creado y guardado exitosamente.");
        } else if (tipoUsuario.equals("profesor")) {
            Profesor profesor = new Profesor(nombreUsuario, nombre, apellido, contraseña);
            persistencia.guardarUsuario(profesor);
            System.out.println("Profesor creado y guardado exitosamente.");
        } else {
            System.out.println("Tipo de usuario no válido.");
        }
    }

    private static void iniciarSesion(Scanner scanner, PersistenciaUsuarios persistenciaUsuarios, PersistenciaResenia persistenciaResenias, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaActividades persistenciaActividades) {
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario usuario = persistenciaUsuarios.obtenerUsuarios().stream()
            .filter(u -> u.getNombreUsuario().equals(nombreUsuario) && u.getContraseña().equals(contraseña))
            .findFirst()
            .orElse(null);

        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso.");
            if (usuario.getTipoUsuario().equals("Estudiante")) {
                opcionesEstudiante(scanner, (Estudiante) usuario, persistenciaLearningPaths, persistenciaResenias);
            } else if (usuario.getTipoUsuario().equals("Profesor")) {
                opcionesProfesor(scanner, (Profesor) usuario, persistenciaLearningPaths, persistenciaActividades, persistenciaUsuarios);
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private static void opcionesEstudiante(Scanner scanner, Estudiante estudiante, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaResenia persistenciaResenias) {
        while (true) {
            System.out.println("Opciones para Estudiante:");
            System.out.println("1. Inscribirse en Learning Path");
            System.out.println("2. Ver Learning Paths inscritos");
            System.out.println("3. Ver actividades de un Learning Path");
            System.out.println("4. Marcar actividad como iniciada/completa");
            System.out.println("5. Marcar Learning Path como completado");
            System.out.println("6. Crear reseña");
            System.out.println("7. Ver progreso");
            System.out.println("8. Ver feedback y notas de actividades");
            System.out.println("9. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (opcion == 1) {
                estudiante.inscribirLearningPath(scanner, persistenciaLearningPaths);
            } else if (opcion == 2) {
                estudiante.mostrarLearningPathsInscritos();
            } else if (opcion == 3) {
                estudiante.verActividadesLearningPath(scanner);
            } else if (opcion == 4) {
                opcionesMarcarActividad(scanner, estudiante, persistenciaLearningPaths);
            } else if (opcion == 5) {
                estudiante.marcarLearningPathCompletado(scanner);
            } else if (opcion == 6) {
                estudiante.crearResenia(scanner, persistenciaLearningPaths, persistenciaResenias);
            } else if (opcion == 7) {
                opcionesVerProgreso(scanner, estudiante);
            } else if (opcion == 8) {
                estudiante.verFeedback(scanner);
            } else if (opcion == 9) {
                break;
            }
        }
    }

    private static void opcionesMarcarActividad(Scanner scanner, Estudiante estudiante, PersistenciaLearningPaths persistenciaLearningPaths) {
        while (true) {
            System.out.println("Opciones para marcar actividad:");
            System.out.println("1. Marcar inicio de actividad");
            System.out.println("2. Marcar actividad como completa");
            System.out.println("3. Volver al menú anterior");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (opcion == 1) {
                estudiante.marcarActividadComoIniciada(scanner, persistenciaLearningPaths);
            } else if (opcion == 2) {
                estudiante.marcarActividadComoCompleta(scanner, persistenciaLearningPaths);
            } else if (opcion == 3) {
                break;
            }
        }
    }

    private static void opcionesVerProgreso(Scanner scanner, Estudiante estudiante) {
        while (true) {
            System.out.println("Opciones de Progreso:");
            System.out.println("1. Ver progreso de actividades");
            System.out.println("2. Ver progreso del Learning Path");
            System.out.println("3. Volver al menú anterior");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (opcion == 1) {
                estudiante.mostrarProgresoActividades(scanner);
            } else if (opcion == 2) {
                estudiante.mostrarProgreso();
            } else if (opcion == 3) {
                break;
            }
        }
    }

    private static void opcionesProfesor(Scanner scanner, Profesor profesor, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaActividades persistenciaActividades,PersistenciaUsuarios persistenciaUsuarios) {
        while (true) {
            System.out.println("Opciones para Profesor:");
            System.out.println("1. Crear Learning Path");
            System.out.println("2. Editar Learning Path");
            System.out.println("3. Ver Learning Paths");
            System.out.println("4. Crear Actividad");
            System.out.println("5. Ver catálogo de actividades");
            System.out.println("6. Agregar actividad a Learning Path");
            System.out.println("7. Calificar actividades de estudiante");
            System.out.println("8. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (opcion == 1) {
                ConsolaProfesor.crearLearningPath(profesor, scanner, persistenciaLearningPaths);
            } else if (opcion == 2) {
                ConsolaProfesor.editarLearningPath(profesor, scanner);
            } else if (opcion == 3) {
                ConsolaProfesor.verLearningPaths(persistenciaLearningPaths);
            } else if (opcion == 4) {
                ConsolaProfesor.crearNuevaActividad(scanner, profesor, persistenciaActividades);
            } else if (opcion == 5) {
                ConsolaActividades.mostrarCatalogo(persistenciaActividades);
            } else if (opcion == 6) {
                ConsolaProfesor.agregarActividadALearningPath(profesor, scanner);
            } else if (opcion == 7) {
            	ConsolaProfesor.calificarActividadesEstudiante(scanner, profesor, persistenciaUsuarios, persistenciaLearningPaths);
            } else if (opcion == 8) {
                break;
            }
        }
    }
}