package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActividades {
	
	private List<Actividad> actividades;
	
public CatalogoActividades() {
	this.actividades = new ArrayList<Actividad>();
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
