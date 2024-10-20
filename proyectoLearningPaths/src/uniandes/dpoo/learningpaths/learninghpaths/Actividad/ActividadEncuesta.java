package uniandes.dpoo.modelo.actividades;

import java.util.ArrayList;
import java.util.List;

public class ActividadEncuesta extends Actividad{
	private List<String> comentarios;
	
	public ActividadEncuesta(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion) {
        super(titulo, descripcion, objetivo, nivelDificultad, duracion);
        this.comentarios = new ArrayList<>();
	}
	
	public List<String> getComentarios(){
		return comentarios;
	}
	
	public void realizar() {
		System.out.println("Enviando comentarios...");
		this.marcarCompletada();
	}
	
	public Actividad clonar() {
		return new ActividadEncuesta(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion());
	}
}
