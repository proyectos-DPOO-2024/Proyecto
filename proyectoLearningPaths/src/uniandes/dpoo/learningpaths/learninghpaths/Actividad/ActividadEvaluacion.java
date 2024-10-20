package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;



public class ActividadEvaluacion extends Actividad{
	private boolean enviada;
    private boolean calificada;
    private boolean exitosa;
    private List<Pregunta> listaPreguntas;
    private double calificacionMinima;
    
    public ActividadEvaluacion(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, double calificacionMinima) {
        super(titulo, descripcion, objetivo, nivelDificultad, duracion);
        this.enviada = false;
        this.calificada = false;
        this.exitosa = false;
        this.listaPreguntas = new ArrayList<>();
		this.calificacionMinima = calificacionMinima;
    }
    
    public boolean getEnviada() {
    	return enviada;
    }
    
    public boolean getCalificada() {
    	return calificada;
    }
    
    public boolean getExitosa() {
    	return exitosa;
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
		System.out.println("Participando en el Examen...");
        this.marcarCompletada();
	}
	
	public void participarEvaluacion() {
		for (int i = 0; i <= listaPreguntas.size(); i++) {
			System.out.println(listaPreguntas.get(i).getEnunciado());
			System.out.println(listaPreguntas.get(i).getOpciones());
		}
		enviada = true;
		realizar();
	}
	
	public void calificarEvaluacion(boolean resultado) {
		if (enviada) {
            this.calificada = true;
            this.exitosa = resultado;
            System.out.println("Examen calificado como " + (exitosa ? "exitoso" : "no exitoso"));
        } else {
            System.out.println("El examen aún no ha sido enviado.");
        }
    }
	
	public Actividad clonar() {
		return new ActividadEvaluacion(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion(), calificacionMinima);
	}
}

