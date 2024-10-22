package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;


import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadQuiz;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Pregunta;

public class PersistenciaActividades implements IPersistenciaActividades {

    @Override
    public void cargarActividades(String archivo, Actividad actividad) throws IOException, InformacionInconsistenteException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            jsonBuilder.append(line);
        }
        br.close();

        JSONArray jsonActividades = new JSONArray(jsonBuilder.toString());
        for (int i = 0; i < jsonActividades.length(); i++) {
            JSONObject jsonActividad = jsonActividades.getJSONObject(i);
            String tipo = jsonActividad.getString("tipo");

            Actividad nuevaActividad = null;

            switch (tipo) {
                case "Quiz":
                    nuevaActividad = cargarActividadQuiz(jsonActividad);
                    break;
                // Puedes agregar otros tipos de actividad aquí
                default:
                    throw new InformacionInconsistenteException("Tipo de actividad desconocido: " + tipo);
            }

            actividad.agregarActividadPrevia(nuevaActividad);
        }
    }

    private ActividadQuiz cargarActividadQuiz(JSONObject jsonActividad) {
        String titulo = jsonActividad.getString("titulo");
        String descripcion = jsonActividad.getString("descripcion");
        String objetivo = jsonActividad.getString("objetivo");
        String nivelDificultad = jsonActividad.getString("nivelDificultad");
        int duracion = jsonActividad.getInt("duracion");
        double calificacionMinima = jsonActividad.getDouble("calificacionMinima");

        ActividadQuiz actividadQuiz = new ActividadQuiz(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacionMinima);

        JSONArray preguntasJson = jsonActividad.getJSONArray("preguntas");
        for (int j = 0; j < preguntasJson.length(); j++) {
            JSONObject jsonPregunta = preguntasJson.getJSONObject(j);
            String enunciado = jsonPregunta.getString("enunciado");
            String correcto = jsonPregunta.getString("correcto");
            String tipoPregunta = jsonPregunta.getString("tipoPregunta");

            Pregunta pregunta = new Pregunta(enunciado, correcto, tipoPregunta);

            JSONArray opcionesJson = jsonPregunta.getJSONArray("opciones");
            for (int k = 0; k < opcionesJson.length(); k++) {
                pregunta.agregarOpcion(opcionesJson.getString(k));
            }

            actividadQuiz.agregarPregunta(pregunta);
        }

        return actividadQuiz;
    }

    @Override
    public void salvarActividades(String archivo, Actividad actividad) throws IOException {
        JSONArray jsonActividades = new JSONArray();

        // Aquí deberías agregar la lógica para convertir la actividad en JSON
        if (actividad instanceof ActividadQuiz) {
            jsonActividades.put(convertirActividadQuizAJson((ActividadQuiz) actividad));
        }
        // Puedes agregar otros tipos de actividad aquí

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(jsonActividades.toString(4)); // Indentación de 4 espacios
        bw.close();
    }

    private JSONObject convertirActividadQuizAJson(ActividadQuiz actividadQuiz) {
        JSONObject jsonActividad = new JSONObject();
        jsonActividad.put("tipo", "Quiz");
        jsonActividad.put("titulo", actividadQuiz.getTitulo());
        jsonActividad.put("descripcion", actividadQuiz.getDescripcion());
        jsonActividad.put("objetivo", actividadQuiz.getObjetivo());
        jsonActividad.put("nivelDificultad", actividadQuiz.getDificultad());
        jsonActividad.put("duracion", actividadQuiz.getDuracion());
        jsonActividad.put("calificacionMinima", actividadQuiz.getCalificacionMinima());

        JSONArray preguntasJson = new JSONArray();
        for (Pregunta pregunta : actividadQuiz.getListaPreguntas()) {
            JSONObject jsonPregunta = new JSONObject();
            jsonPregunta.put("enunciado", pregunta.getEnunciado());
            jsonPregunta.put("correcto", pregunta.getCorrecto());
            jsonPregunta.put("tipoPregunta", pregunta.getTipoPregunta());

            JSONArray opcionesJson = new JSONArray(pregunta.getOpciones());
            jsonPregunta.put("opciones", opcionesJson);

            preguntasJson.put(jsonPregunta);
        }

        jsonActividad.put("preguntas", preguntasJson);
        return jsonActividad;
    }
}
