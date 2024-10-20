package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

public class Reseña {
	
	private String reseñaText;
	private int calificacion; // Se puntua de 1 a 5
	public Reseña(String reseñaText, int calificacion) {
		this.reseñaText = reseñaText;
		this.calificacion = calificacion;
	}
	public String getReseñaText() {
		return reseñaText;
	}
	public void setReseñaText(String reseñaText) {
		this.reseñaText = reseñaText;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
	
}
