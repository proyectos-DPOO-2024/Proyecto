package uniandes.dpoo.learningpaths.usuarios;

import uniandes.dpoo.learningpaths.persistencias.IPersistenciaUsuarios;
import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private int contadorID = 0; // Contador para generar IDs únicos
    private List<Usuario> usuarios; // Lista de usuarios cargados
    private IPersistenciaUsuarios persistenciaUsuarios; // Maneja la persistencia
    private String archivoUsuarios; // Archivo donde se guardan los usuarios

    // Constructor que acepta una implementación de IPersistenciaUsuarios
    public UsuarioManager(IPersistenciaUsuarios persistenciaUsuarios, String archivoUsuarios) {
        this.persistenciaUsuarios = persistenciaUsuarios;
        this.archivoUsuarios = archivoUsuarios;
        this.usuarios = new ArrayList<>();

        // Cargar los usuarios del archivo al iniciar el manager
        try {
            for (Usuario usuario : persistenciaUsuarios.cargarUsuarios(archivoUsuarios)) {
                usuarios.add(usuario);
                int id = Integer.parseInt(usuario.getUsuarioID());
                if (id > contadorID) {
                    contadorID = id;
                }
            }
        } catch (IOException | InformacionInconsistenteException e) {
            e.printStackTrace();
        }
    }

    // Método para generar un ID único
    public String generarID() {
        contadorID++;
        return String.valueOf(contadorID); // Devuelve el ID como String
    }

    // Crear un nuevo Estudiante y agregarlo a la lista
    public Estudiante crearEstudiante(String nombreUsuario, String nombre, String apellido, String contraseña) {
        String usuarioID = generarID();
        Estudiante estudiante = new Estudiante(usuarioID, nombreUsuario, nombre, apellido, contraseña);
        usuarios.add(estudiante);
        guardarUsuarios(); // Guardar cambios
        return estudiante;
    }

    // Crear un nuevo Profesor y agregarlo a la lista
    public Profesor crearProfesor(String nombreUsuario, String nombre, String apellido, String contraseña) {
        String usuarioID = generarID();
        Profesor profesor = new Profesor(usuarioID, nombreUsuario, nombre, apellido, contraseña);
        usuarios.add(profesor);
        guardarUsuarios(); // Guardar cambios
        return profesor;
    }

    // Obtener la lista de usuarios
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Guardar los usuarios en el archivo
    public void guardarUsuarios() {
        try {
            persistenciaUsuarios.salvarUsuario(archivoUsuarios, usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
