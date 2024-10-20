package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
	private String enunciado;
	private List<String> opciones;
	private String correcto;
	private String tipoPregunta;
	
	public Pregunta(String enunciado, String correcto, String tipoPregunta) {
		this.enunciado = enunciado;
		this.opciones = new ArrayList<>();
		this.correcto = correcto;
		this.tipoPregunta = tipoPregunta;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public List<String> getOpciones() {
		return opciones;
	}
	
	public String getCorrecto() {
		return correcto;
	}
	
	public String getTipoPregunta() {
		return tipoPregunta;
	}
	
	public void preguntaAbierta() {
		if (tipoPregunta == "abierta") {
			opciones.clear();
		}	
	}
	
	public void agregarOpcion(String opcion) {
		opciones.add(opcion);
	}
	
	public void eliminarOpcion(int indice) {
		if (indice >= 0 && indice < opciones.size()) {
			opciones.remove(indice);
		} else {
			System.out.println("Índice fuera de rango, no se pudo eliminar la opcion.");
		}
	}
	
	public void marcarComoCorrecto(String opcion) {
		this.correcto = opcion;
	}
}
