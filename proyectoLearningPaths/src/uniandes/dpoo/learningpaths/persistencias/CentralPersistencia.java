package uniandes.dpoo.learningpaths.persistencias;


import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Pregunta;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.usuario.Usuario;

/**
 * Clase para manejar la persistencia de actividades, preguntas, learning paths y usuarios en diferentes formatos.
 */
public class CentralPersistencia {

    // Tipos de archivo soportados
    public static final String JSON = "JSON";
    public static final String PLAIN = "PlainText";

    /**
     * Retorna una nueva instancia de una clase capaz de cargar y salvar las actividades.
     *
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de las actividades.
     * @return El objeto que debería usarse para cargar y salvar la información.
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido.
     */
    public static IPersistenciaActividades getPersistenciaActividades(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equals(tipoArchivo)) {
            return new PersistenciaActividadesJson(); // Implementa esta clase
        } else if (PLAIN.equals(tipoArchivo)) {
            return new PersistenciaActividadesPlaintext(); // Implementa esta clase
        } else {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }

    /**
     * Retorna una nueva instancia de una clase capaz de cargar y salvar los learning paths.
     *
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los learning paths.
     * @return El objeto que debería usarse para cargar y salvar la información.
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido.
     */
    public static IPersistenciaLearningPaths getPersistenciaLearningPaths(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equals(tipoArchivo)) {
            return new PersistenciaLearningPathsJson(); // Implementa esta clase
        } else if (PLAIN.equals(tipoArchivo)) {
            return new PersistenciaLearningPathsPlaintext(); // Implementa esta clase
        } else {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }

    /**
     * Retorna una nueva instancia de una clase capaz de cargar y salvar los usuarios.
     *
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los usuarios.
     * @return El objeto que debería usarse para cargar y salvar la información.
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido.
     */
    public static IPersistenciaUsuarios getPersistenciaUsuarios(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equals(tipoArchivo)) {
            return new PersistenciaUsuariosJson(); // Implementa esta clase
        } else if (PLAIN.equals(tipoArchivo)) {
            return new PersistenciaUsuariosPlaintext(); // Implementa esta clase
        } else {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }
}

