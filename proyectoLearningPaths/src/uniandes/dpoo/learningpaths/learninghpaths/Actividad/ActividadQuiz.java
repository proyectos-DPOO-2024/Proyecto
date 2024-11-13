package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;


public class ActividadQuiz extends Actividad {
	
	private List<Pregunta> pregunta;

    public ActividadQuiz(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion) {

        super(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
        
        this.setPregunta(new ArrayList<Pregunta>());
        
    }

	public List<Pregunta> getPregunta() {
		return pregunta;
	}

	public void setPregunta(List<Pregunta> pregunta) {
		this.pregunta = pregunta;
	}

}