package uniandes.dpoo.learningpaths.persistencias;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;

public class PersistenciaLearningPaths {

	private List<LearningPath> learningPaths = new ArrayList<>();

    public void guardarLearningPath(LearningPath learningPath) {
        learningPaths.add(learningPath);
    }

    public List<LearningPath> obtenerLearningPaths() {
        return learningPaths;
    }
}
