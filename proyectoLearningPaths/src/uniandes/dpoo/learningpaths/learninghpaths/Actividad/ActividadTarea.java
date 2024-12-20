package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.awt.Component;

public class ActividadTarea extends Actividad {
    public ActividadTarea(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion) {
        super(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
    }
    
    public void realizar() {
        System.out.println("Realizando la tarea...");
        this.marcarCompletada();
    }
    
    public Actividad clonar() {
        return new ActividadTarea(getTitulo(), getDescripcion(), getObjetivo(), getNivelDificultad(), getDuracion(), getCalificacion());
    }

	@Override
	public Component getEstudiantesCompletados() {
		// TODO Auto-generated method stub
		return null;
	}
}

