package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActividades {
	
	private static CatalogoActividades instancia;
	private List<Actividad> actividades;
	
public CatalogoActividades() {
	this.actividades = new ArrayList<Actividad>();
}

public static CatalogoActividades obtenerInstancia() {
    if (instancia == null) { // Si la instancia aún no ha sido creada
        instancia = new CatalogoActividades(); // Crear la instancia
    }
    return instancia; // Devolver la instancia
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
