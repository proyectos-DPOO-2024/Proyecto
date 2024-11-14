package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected boolean enviada;
    protected boolean calificada;
    protected boolean exitosa;
	protected Float calificacion;
	protected String titulo;
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected int duracion;
	protected List<Actividad> actividadesPrevias;
	protected boolean completada;
	protected LocalDateTime fechaCreacion;
	protected LocalDateTime fechaModificacion;
	private static int contadorID = 0;
	private int actividadID;
    
    public Actividad(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion) {
        this.enviada = false;
        this.calificada = false;
        this.exitosa = false;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.nivelDificultad = nivelDificultad;
        this.duracion = duracion;
        this.actividadesPrevias = new ArrayList<>();
        this.completada = false;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
        this.actividadID = contadorID++;
        
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

    public boolean getEnviada() {
    	return enviada;
    }
    
    public boolean getCalificada() {
    	return calificada;
    }
    
    public boolean getExitosa() {
    	return exitosa;
    }
    
    
    public String getNivelDificultad() {
		return nivelDificultad;
	}
    

	public Float getCalificacion() {
		return calificacion;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setActividadesPrevias(List<Actividad> actividadesPrevias) {
		this.actividadesPrevias = actividadesPrevias;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getActividadID() {
		return actividadID;
	}
	public void marcarCompletada() {
    		this.completada = true;
    }
}

