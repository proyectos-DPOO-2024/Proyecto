package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;


public class ActividadQuiz extends Actividad {
	
	private List<Pregunta> listaPreguntas;
	private double calificacionMinima;
	
	public ActividadQuiz(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, double calificacionMinima) {
		super(titulo, descripcion, objetivo, nivelDificultad, duracion);
		this.listaPreguntas = new ArrayList<>();
		this.calificacionMinima = calificacionMinima;
	}
	
	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}
	
	public double getCalificacionMinima() {
		return calificacionMinima;
	}
	
	public void agregarPregunta(Pregunta pregunta) {
		listaPreguntas.add(pregunta);
	}
	
	public void eliminarPregunta(int indice) {
		if (indice >= 0 && indice < listaPreguntas.size()) {
            listaPreguntas.remove(indice);
		} else {
			System.out.println("Índice fuera de rango, no se pudo eliminar la pregunta.");
		}
	}
	
	public void realizar() {
        System.out.println("Participando en el quiz...");
        this.marcarCompletada();
    }
	
	public void participarQuiz() {
		for (int i = 0; i <= listaPreguntas.size(); i++) {
			System.out.println(listaPreguntas.get(i).getEnunciado());
			System.out.println(listaPreguntas.get(i).getOpciones());
		}
		realizar();
	}
	
	public Actividad clonar() {
		return new ActividadQuiz(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion(), calificacionMinima);
	}

}
