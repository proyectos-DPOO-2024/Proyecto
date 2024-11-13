package uniandes.dpoo.learningpaths.learninghpaths;

import java.util.ArrayList;
import java.util.List;

public class CatalogoLearningPaths {

	private static CatalogoLearningPaths instancia;
	private List<LearningPath> catalogoLearningPaths;

	public CatalogoLearningPaths() {
		this.catalogoLearningPaths = new ArrayList<LearningPath>();
	}

	public static CatalogoLearningPaths obtenerInstancia() {
        if (instancia == null) { // Si la instancia aún no ha sido creada
            instancia = new CatalogoLearningPaths(); // Crear la instancia
        }
        return instancia; // Devolver la instancia
    }
	
	public List<LearningPath> getCatalogoLearningPaths() {
		return catalogoLearningPaths;
	}

	public void setCatalogoLearningPaths(List<LearningPath> catalogoLearningPaths) {
		this.catalogoLearningPaths = catalogoLearningPaths;
	}
	
	public void agregarLearningPath(LearningPath learningpath) {
		this.catalogoLearningPaths.add(learningpath);
		
	}
	
}
