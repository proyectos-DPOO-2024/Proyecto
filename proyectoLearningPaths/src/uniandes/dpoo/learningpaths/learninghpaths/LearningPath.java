package uniandes.dpoo.learningpaths.learninghpaths;


import java.util.List;

public class LearningPath {
	
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private int duracion;
	private double rating;
	private List<Actividad> actividades;
	
	public LearningPath(String titulo, String descripcion, int duracion, String dificultad, double rating, List<Actividad> actividades) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.duracion = duracion;
		this.rating = rating;
		this.actividades = actividades;
		
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	
	public double calcularProgreo() {
		int completadas = 0;
		for (Actividad actividad : actividades) {
			if (actividad.isCompletada()) {
				completadas++;
			}
		}
		return (double) completadas / actividades.size() * 100; 
		
	}
	
	
	
	
}
