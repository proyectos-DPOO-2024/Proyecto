package uniandes.dpoo.learningpaths.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpaths.usuarios.Usuario;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;

public class Consola {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<LearningPath> learningPaths = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Bienvenido");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. registrarce");

            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    crearUsuario(scanner);
                    break;
                case 2:
                    crearLearningPath(scanner);
                    break;
                case 3:
                    asignarLearningPath(scanner);
                    break;
                case 4:
                    mostrarLearningPaths(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void crearUsuario(Scanner scanner) {
        System.out.print("Ingrese ID de Usuario: ");
        String usuarioID = scanner.nextLine();
        System.out.print("Ingrese Nombre de Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario usuario = new Usuario(usuarioID, nombreUsuario, nombre, apellido, contraseña) {};
        usuarios.add(usuario);
        System.out.println("Usuario creado exitosamente.");
    }

    private static void crearLearningPath(Scanner scanner) {
        System.out.print("Ingrese ID de LearningPath: ");
        String learningpathID = scanner.nextLine();
        System.out.print("Ingrese Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese Duración: ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese Nivel de Dificultad: ");
        String dificultad = scanner.nextLine();
        System.out.print("Ingrese Rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        List<Actividad> actividades = new ArrayList<>(); // Aquí puedes agregar lógica para crear actividades
        LearningPath learningPath = new LearningPath(learningpathID, titulo, descripcion, duracion, dificultad, rating, actividades, 0);
        learningPaths.add(learningPath);
        System.out.println("LearningPath creado exitosamente.");
    }

    private static void asignarLearningPath(Scanner scanner) {
        System.out.print("Ingrese ID de Usuario: ");
        String usuarioID = scanner.nextLine();
        System.out.print("Ingrese ID de LearningPath: ");
        String learningpathID = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorID(usuarioID);
        LearningPath learningPath = buscarLearningPathPorID(learningpathID);

        if (usuario != null && learningPath != null) {
            // Aquí puedes agregar lógica para asignar el LearningPath al Usuario
            System.out.println("LearningPath asignado exitosamente.");
        } else {
            System.out.println("Usuario o LearningPath no encontrado.");
        }
    }

    private static void mostrarLearningPaths(Scanner scanner) {
        System.out.print("Ingrese ID de Usuario: ");
        String usuarioID = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorID(usuarioID);

        if (usuario != null) {
            // Aquí puedes agregar lógica para mostrar los LearningPaths del Usuario
            System.out.println("Mostrando LearningPaths del Usuario.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static Usuario buscarUsuarioPorID(String usuarioID) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuarioID().equals(usuarioID)) {
                return usuario;
            }
        }
        return null;
    }

    private static LearningPath buscarLearningPathPorID(String learningpathID) {
        for (LearningPath learningPath : learningPaths) {
            if (learningPath.getLearningpathID().equals(learningpathID)) {
                return learningPath;
            }
        }
        return null;
    }
}