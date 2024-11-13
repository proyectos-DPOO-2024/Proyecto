package uniandes.dpoo.learningpaths.learninghpaths;

import java.util.List;

public class CatalogoLearningPaths {

	private List<LearningPath> catalogoLearningPaths;

	public CatalogoLearningPaths(List<LearningPath> catalogoLearningPaths) {
		this.catalogoLearningPaths = catalogoLearningPaths;
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
