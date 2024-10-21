package uniandes.dpoo.learningpaths.usuarios;

public class UsuarioManager {
    private int contadorID = 0; // Contador para generar IDs únicos

    public String generarID() {
        contadorID++;
        return String.valueOf(contadorID); // Devuelve el ID como String
    }

    public Estudiante crearEstudiante(String nombreUsuario, String nombre, String apellido, String contraseña) {
        String usuarioID = generarID();
        return new Estudiante(usuarioID, nombreUsuario, nombre, apellido, contraseña);
    }

    public Profesor crearProfesor(String nombreUsuario, String nombre, String apellido, String contraseña) {
        String usuarioID = generarID();
        return new Profesor(usuarioID, nombreUsuario, nombre, apellido, contraseña);
    }
}
