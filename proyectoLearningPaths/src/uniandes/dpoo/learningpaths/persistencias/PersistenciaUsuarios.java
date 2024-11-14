package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.usuarios.Usuario;

public class PersistenciaUsuarios {

    private List<Usuario> usuarios = new ArrayList<>();
    private static final String FILE_PATH = "usuarios.ser";

    public PersistenciaUsuarios() {
        cargarUsuarios();
    }

    public void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados en " + new File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarUsuarios() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Archivo " + FILE_PATH + " no encontrado. Creando nuevo archivo.");
            return; 
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            usuarios = (List<Usuario>) ois.readObject();
            System.out.println("Usuarios cargados desde " + file.getAbsolutePath());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
