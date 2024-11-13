package uniandes.dpoo.learningpaths.learninghpaths;


import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.CatalogoActividades;

public class LearningPath {
	
	private static int contadorID = 0;
	private int learningpathID;
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private int duracion;
	private double rating;
	private List<Actividad> actividades;
	
	public LearningPath(String titulo, String descripcion, int duracion, String dificultad, double rating) {
		this.learningpathID = contadorID++;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = dificultad;
		this.duracion = duracion;
		this.rating = rating;
		this.actividades = new ArrayList<Actividad>();
		
	}

	public String getTitulo() {
		return titulo;
	}

	
	public int getLearningpathID() {
		return learningpathID;
	}

	public void setLearningpathID(int learningpathID) {
		this.learningpathID = learningpathID;
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
	

	
	public void agregarActividad (Actividad actividad) {
		
		this.actividades.add(actividad);
	}
	
	
	
}
