package uniandes.dpoo.learningpaths.persistencias;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.usuarios.Usuario;
import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.usuarios.Profesor;

public class PersistenciaUsuarios implements IPersistenciaUsuarios {

    private static final String USUARIO_ID = "usuarioID";
    private static final String NOMBRE_USUARIO = "nombreUsuario";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDO = "apellido";
    private static final String CONTRASENA = "contrasena";
    private static final String TIPO_USUARIO = "tipoUsuario";
    private static final String ESTUDIANTE = "Estudiante";
    private static final String PROFESOR = "Profesor";

    @Override
    public void cargarUsuario(String archivo, Usuario usuario) throws IOException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        
        JSONArray usuariosArray = raiz.getJSONArray("usuarios");
        cargarUsuarios(usuario, usuariosArray);
    }

    @Override
    public void guardarUsuarios(String archivo, List<Usuario> usuarios) throws IOException {
        JSONObject jobject = new JSONObject();
        JSONArray jUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            JSONObject jUsuario = new JSONObject();
            jUsuario.put(USUARIO_ID, usuario.getUsuarioID());
            jUsuario.put(NOMBRE_USUARIO, usuario.getNombreUsuario());
            jUsuario.put(NOMBRE, usuario.getNombre());
            jUsuario.put(APELLIDO, usuario.getApellido());
            jUsuario.put(CONTRASENA, usuario.getContraseña());
            
            if (usuario instanceof Estudiante) {
                jUsuario.put(TIPO_USUARIO, ESTUDIANTE);
            } else if (usuario instanceof Profesor) {
                jUsuario.put(TIPO_USUARIO, PROFESOR);
            }

            jUsuarios.put(jUsuario);
        }
        
        jobject.put("usuarios", jUsuarios);

        // Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter(archivo);
        jobject.write(pw, 2, 0);
        pw.close();
    }


    private void cargarUsuarios(Usuario usuario, JSONArray jUsuarios) throws InformacionInconsistenteException {
        int numUsuarios = jUsuarios.length();
        for (int i = 0; i < numUsuarios; i++) {
            JSONObject jUsuario = jUsuarios.getJSONObject(i);
            String tipoUsuario = jUsuario.getString(TIPO_USUARIO);
            Usuario nuevoUsuario = null;

            if (ESTUDIANTE.equals(tipoUsuario)) {
                nuevoUsuario = new Estudiante(
                    jUsuario.getString(USUARIO_ID),
                    jUsuario.getString(NOMBRE_USUARIO),
                    jUsuario.getString(NOMBRE),
                    jUsuario.getString(APELLIDO),
                    jUsuario.getString(CONTRASENA)
                );
            } else if (PROFESOR.equals(tipoUsuario)) {
                nuevoUsuario = new Profesor(
                    jUsuario.getString(USUARIO_ID),
                    jUsuario.getString(NOMBRE_USUARIO),
                    jUsuario.getString(NOMBRE),
                    jUsuario.getString(APELLIDO),
                    jUsuario.getString(CONTRASENA)
                );
            }

            if (nuevoUsuario != null) {
                // Asignar el nuevo usuario a alguna estructura en la lógica de usuarios
                // (Ej: agregar a un UsuarioManager, o similar)
            } else {
                throw new InformacionInconsistenteException("Tipo de usuario no reconocido: " + tipoUsuario);
            }
        }
    }

    private void salvarUsuarios(Usuario usuario, JSONObject jobject) {
        JSONArray jUsuarios = new JSONArray();
        
        // Aquí deberías iterar sobre todos los usuarios que se necesitan guardar
        // Este es un ejemplo genérico de cómo se puede guardar un usuario.
        JSONObject jUsuario = new JSONObject();
        jUsuario.put(USUARIO_ID, usuario.getUsuarioID());
        jUsuario.put(NOMBRE_USUARIO, usuario.getNombreUsuario());
        jUsuario.put(NOMBRE, usuario.getNombre());
        jUsuario.put(APELLIDO, usuario.getApellido());
        jUsuario.put(CONTRASENA, usuario.getContraseña());
        
        if (usuario instanceof Estudiante) {
            jUsuario.put(TIPO_USUARIO, ESTUDIANTE);
        } else if (usuario instanceof Profesor) {
            jUsuario.put(TIPO_USUARIO, PROFESOR);
        }

        jUsuarios.put(jUsuario);
        
        jobject.put("usuarios", jUsuarios);
    }
}
