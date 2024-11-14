package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

public class ActividadTarea extends Actividad {

    public ActividadTarea(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion) {

        super(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);

    }
    
    public void realizar() {
        System.out.println("Realizando la tarea...");
        this.marcarCompletada();
    }
    
    public Actividad clonar() {
    	return new ActividadTarea(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion());
    }
}

