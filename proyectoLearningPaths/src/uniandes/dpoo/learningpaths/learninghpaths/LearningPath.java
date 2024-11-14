package uniandes.dpoo.learningpaths.learninghpaths;


import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;

public class LearningPath {
    private static int contadorID = 0;
    private int learningpathID;
    private String titulo;
    private String descripcion;
    private String nivelDificultad;
    private int duracion;
    private double rating;
    private List<Reseña> reseñas;
    private List<Actividad> actividades;

    public LearningPath(String titulo, String descripcion, int duracion, String dificultad, double rating) {
        this.learningpathID = contadorID++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nivelDificultad = dificultad;
        this.duracion = duracion;
        this.rating = rating;
        this.reseñas = new ArrayList<>();
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

    public void agregarResenia(Reseña reseña) {
        this.reseñas.add(reseña);
    }

    public List<Reseña> getResenias() {
        return reseñas;
    }

    public void mostrarResenias() {
        if (reseñas.isEmpty()) {
            System.out.println("No hay reseñas.");
        } else {
            System.out.println("Reseñas:");
            for (Reseña resenia : reseñas) {
                System.out.println("- " + resenia.getCalificacion());
                System.out.println("- " + resenia.getReseñaText());
                
            }
        }
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public void mostrarActividades() {
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades.");
        } else {
            System.out.println("Actividades:");
            for (Actividad actividad : actividades) {
                System.out.println("- " + actividad.getTitulo());
            }
        }
    }
}



