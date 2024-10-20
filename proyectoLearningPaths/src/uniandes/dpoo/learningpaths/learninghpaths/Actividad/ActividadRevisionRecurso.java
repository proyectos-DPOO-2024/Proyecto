package uniandes.dpoo.modelo.actividades;

public class ActividadRevisionRecurso extends Actividad {
	private String tipoRecurso;
	
	public ActividadRevisionRecurso(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, String tipoRecurso) {
		super(titulo, descripcion, objetivo, nivelDificultad, duracion);
		this.tipoRecurso = tipoRecurso;
	}
	
	public String getTipoRecurso() {
		return tipoRecurso;
	}
	
	public void realizar() {
        System.out.println("Accediendo al recurso: " + tipoRecurso);
        this.marcarCompletada();
	}
	
	public Actividad clonar() {
        return new ActividadRevisionRecurso(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion(), tipoRecurso);
    }
}
