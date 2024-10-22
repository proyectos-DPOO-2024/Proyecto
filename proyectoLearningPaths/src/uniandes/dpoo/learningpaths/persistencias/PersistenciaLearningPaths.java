package uniandes.dpoo.learningpaths.persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;

public class PersistenciaLearningPaths implements IPersistenciaLeaningPaths {

    @Override
    public void cargarLearningPath(String archivo, LearningPath learningPath) throws IOException, InformacionInconsistenteException {
        // Leer el archivo y convertir el JSON en un objeto LearningPath
        String contenido = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONObject jsonLearningPath = new JSONObject(contenido);
        
        // Llenar el objeto learningPath con la información del JSON
        learningPath.setTitulo(jsonLearningPath.getString("titulo"));
        learningPath.setDescripcion(jsonLearningPath.getString("descripcion"));
        learningPath.setNivelDificultad(jsonLearningPath.getString("nivelDificultad"));
        learningPath.setDuracion(jsonLearningPath.getInt("duracion"));
        learningPath.setRating(jsonLearningPath.getDouble("rating"));

        // Cargar actividades
        JSONArray actividadesArray = jsonLearningPath.getJSONArray("actividades");
        for (int i = 0; i < actividadesArray.length(); i++) {
            JSONObject jsonActividad = actividadesArray.getJSONObject(i);
            // Cargar cada actividad según su tipo
            cargarActividad(learningPath, jsonActividad);
        }
        
        // Verificar si los datos cargados son consistentes
        if (learningPath.getTitulo() == null || learningPath.getDescripcion() == null) {
            throw new InformacionInconsistenteException("El archivo tiene información incompleta.");
        }
    }

    @Override
    public void salvarLearningPath(String archivo, LearningPath learningPath) throws IOException {
        // Convertir el objeto LearningPath en un JSON
        JSONObject jsonLearningPath = new JSONObject();
        jsonLearningPath.put("titulo", learningPath.getTitulo());
        jsonLearningPath.put("descripcion", learningPath.getDescripcion());
        jsonLearningPath.put("nivelDificultad", learningPath.getNivelDificultad());
        jsonLearningPath.put("duracion", learningPath.getDuracion());
        jsonLearningPath.put("rating", learningPath.getRating());

        // Guardar las actividades en el JSON
        JSONArray actividadesArray = new JSONArray();
        learningPath.getActividades().forEach(actividad -> {
            JSONObject jsonActividad = new JSONObject();
            // Asumiendo que hay un método para convertir actividad a JSON
            jsonActividad = convertirActividadAJSON(actividad);
            actividadesArray.put(jsonActividad);
        });
        jsonLearningPath.put("actividades", actividadesArray);

        // Guardar el JSON en el archivo
        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonLearningPath.toString());
            file.flush();
        }
    }

    // Método auxiliar para cargar las actividades desde un JSON
    private void cargarActividad(LearningPath learningPath, JSONObject jsonActividad) {
        // Aquí debes implementar cómo cargar cada tipo de actividad
        // y añadirla al learningPath
    }

    // Método auxiliar para convertir una actividad en JSON
    private JSONObject convertirActividadAJSON(Object actividad) {
        // Aquí debes implementar la conversión de cada actividad a JSON
        return new JSONObject();
    }
}
