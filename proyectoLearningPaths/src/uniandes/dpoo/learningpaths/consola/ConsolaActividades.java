package uniandes.dpoo.learningpaths.consola;

import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.CatalogoActividades;

public class ConsolaActividades {

	public static void mostrarCatalogo() {
	       // Obtener la instancia del catálogo de actividades
		CatalogoActividades catalogo = CatalogoActividades.obtenerInstancia();
	        
	        // Obtener la lista de actividades
	    List<Actividad> actividades = catalogo.getActividades();
	        
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
