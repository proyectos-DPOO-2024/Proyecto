package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public abstract class Actividad {
	protected String titulo;
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected int duracion;
	protected List<Actividad> actividadesPrevias;
	protected boolean completada;
	protected LocalDateTime fechaCreacion;
	protected LocalDateTime fechaModificacion;
	private List<Reseña> reseñas;
    
    public Actividad(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.nivelDificultad = nivelDificultad;
        this.duracion = duracion;
        this.actividadesPrevias = new ArrayList<>();
        this.completada = false;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
        this.reseñas = new ArrayList<Reseña>();
    }
    
    public String getTitulo() {
    	return titulo;
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public String getObjetivo() {
    	return objetivo;
    }
    
    public String getDificultad() {
    	return nivelDificultad;
    }
    
    public int getDuracion() {
    	return duracion;
    }
    
    public List<Actividad> getActividadesPrevias() {
    	return actividadesPrevias;
    }
    
    public boolean getCompletada() {
    	return completada;
    }
    
    public LocalDateTime getFechaCreacion() {
    	return fechaCreacion;
    }
    
    public LocalDateTime getFechaModificacion() {
    	return fechaModificacion;
    }

    
    public List<Reseña> getReseñas() {
		return reseñas;
	}
    
    public void agregarReseña(Reseña reseña) {
    	this.reseñas.add(reseña);
    }
    

	public void marcarCompletada() {
    	this.completada = true;
    }
	
    public abstract Actividad clonar();
    
    public void editarActividad(String nuevoTitulo, String nuevaDescripcion, String nuevoObjetivo, String nuevoNivelDificultad, int nuevaDuracion) {
    	this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.objetivo = nuevoObjetivo;
        this.nivelDificultad = nuevoNivelDificultad;
        this.duracion = nuevaDuracion;
        this.fechaModificacion = LocalDateTime.now();
    }
    public void agregarActividadPrevia(Actividad actividadPrevia) {
        actividadesPrevias.add(actividadPrevia);
    }
    public boolean verificarPrerequisitosCompletados() {
        for (Actividad previa : actividadesPrevias) {
            if (!previa.completada) {
                return false;
            }
        }
        return true;
    }
    
    public abstract void realizar();
    
    
    
}
