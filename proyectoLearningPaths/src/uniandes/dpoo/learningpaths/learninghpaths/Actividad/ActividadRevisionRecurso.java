package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

public class ActividadRevisionRecurso extends Actividad {
	private String tipoRecurso;
	
	public ActividadRevisionRecurso(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, String tipoRecurso, Float calificacion) {
		super(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
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
        return new ActividadRevisionRecurso(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion(), tipoRecurso, getCalificacion());
    }

	@Override
	public void clificar() {
		// TODO Auto-generated method stub
		
	}
}
