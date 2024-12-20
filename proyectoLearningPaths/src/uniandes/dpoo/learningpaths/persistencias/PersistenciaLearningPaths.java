package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;

public class PersistenciaLearningPaths {

    private ArrayList<LearningPath> learningPaths = new ArrayList<>();
    private static final String FILE_PATH = "learningpaths.ser";

    public PersistenciaLearningPaths() {
        cargarLearningPaths();
    }

    public void guardarLearningPath(LearningPath learningPath) {
        learningPaths.add(learningPath);
        guardarLearningPaths();
    }

    public ArrayList<LearningPath> obtenerLearningPaths() {
        return learningPaths;
    }

    private void guardarLearningPaths() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(learningPaths);
            System.out.println("Learning Paths guardados en " + new File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	private void cargarLearningPaths() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Archivo " + FILE_PATH + " no encontrado. Creando nuevo archivo.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            learningPaths = (ArrayList<LearningPath>) ois.readObject();
            learningPaths.removeIf(Objects::isNull);
            System.out.println("Learning Paths cargados desde " + file.getAbsolutePath());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarLearningPath(String titulo) {
        learningPaths.removeIf(lp -> lp.getTitulo().equalsIgnoreCase(titulo));
    }



}