package uniandes.dpoo.learningpaths.usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private List<Usuario> usuarios;
    private int idCounter;

    public UsuarioManager() {
        usuarios = new ArrayList<>();
        idCounter = 1; // Comienza en 1
    }

    public String generarUsuarioID() {
        return String.valueOf(idCounter++); // Incrementa el contador y devuelve el nuevo ID
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
