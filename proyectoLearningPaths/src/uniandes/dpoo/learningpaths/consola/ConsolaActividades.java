package uniandes.dpoo.learningpaths.consola;

import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.CatalogoActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;

public class ConsolaActividades {

	public static void mostrarCatalogo(PersistenciaActividades persistenciactividades) {
	       // Obtener la instancia del catálogo de actividades
	        // Obtener la lista de actividades
	    List<Actividad> actividades = persistenciactividades.obtenerActividades();
	        
	        // Mostrar las actividades en el catálogo
	    if (actividades.isEmpty()) {
	    	System.out.println("No hay actividades disponibles en el catálogo.");
	        } 
	    else {
	         System.out.println("Catálogo de Actividades:");
	         for (int i = 0; i < actividades.size(); i++) {
	                Actividad actividad = actividades.get(i);
	                System.out.println((i + 1) + ". " + actividad.getTitulo() + " - " + actividad.getDescripcion() + "-" + actividad.getActividadID());
	            }
	        }
	    }
}
