package uniandes.dpoo.learningpaths.persistencias;

import java.util.ArrayList;
import java.util.List;


import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.usuarios.Usuario;
import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.usuarios.Profesor;

public class PersistenciaUsuarios {

    private List<Usuario> usuarios = new ArrayList<>();

    public void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}
