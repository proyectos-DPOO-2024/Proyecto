package uniandes.dpoo.learningpaths.learninghpaths;

import java.util.ArrayList;
import java.util.List;

public class LearningPathManager {
    private List<LearningPath> learningPaths;
    private int idCounter;

    public LearningPathManager() {
        learningPaths = new ArrayList<>();
        idCounter = 1; // Comienza en 1
    }

    public String generarLearningPathID() {
        return "LP" + idCounter++; // Genera un ID único, prefijado con "LP"
    }

    public void agregarLearningPath(LearningPath path) {
        learningPaths.add(path);
    }

    public List<LearningPath> getLearningPaths() {
        return learningPaths;
    }
}
