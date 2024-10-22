package uniandes.dpoo.learningpaths.learninghpaths;


import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;

public class LearningPath {
	
	private String learningpathID;
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private int duracion;
	private double rating;
	private List<Actividad> actividades;
	private double progreso;
	
	public LearningPath(String learningpathID, String titulo, String descripcion, int duracion, String dificultad, double rating, List<Actividad> actividades,double progreso) {
		this.learningpathID = learningpathID;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = dificultad;
		this.duracion = duracion;
		this.rating = rating;
		this.actividades = actividades;
		this.progreso = progreso;
		
		
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

	public void setNivelDificultad(String i) {
		this.nivelDificultad = i;
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
	
	public double getProgreso() {
		return progreso;
	}

	public double calcularProgreo() {
		int completadas = 0;
		for (Actividad actividad : actividades) {
			if (actividad.getCompletada()) {
				completadas++;
			}
		}
		this.progreso = (double) completadas / actividades.size() * 100; 
		
		return progreso;
		
	}
	
	
	
	
}
