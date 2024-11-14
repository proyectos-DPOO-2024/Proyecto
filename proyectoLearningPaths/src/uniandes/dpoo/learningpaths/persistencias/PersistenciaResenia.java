package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaResenia {

    private List<String> reseñas = new ArrayList<>();
    private static final String FILE_PATH = "resenias.ser";

    public PersistenciaResenia() {
        cargarResenias();
    }

    public void guardarReseña(String reseña) {
        reseñas.add(reseña);
        guardarResenias();
    }

    public List<String> obtenerReseñas() {
        return reseñas;
    }

    private void guardarResenias() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(reseñas);
            System.out.println("Reseñas guardadas en " + new File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarResenias() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Archivo " + FILE_PATH + " no encontrado. Creando nuevo archivo.");
            return; // Si el archivo no existe, no hay nada que cargar
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            reseñas = (List<String>) ois.readObject();
            System.out.println("Reseñas cargadas desde " + file.getAbsolutePath());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}