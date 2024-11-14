package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;

public class PersistenciaActividades {

    private List<Actividad> actividades = new ArrayList<>();
    private static final String FILE_PATH = "actividades.ser";

    public PersistenciaActividades() {
        cargarActividades();
    }

    public void guardarActividad(Actividad actividad) {
        actividades.add(actividad);
        guardarActividades();
    }

    public List<Actividad> obtenerActividades() {
        return actividades;
    }

    private void guardarActividades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(actividades);
            System.out.println("Actividades guardadas en " + new File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarActividades() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Archivo " + FILE_PATH + " no encontrado. Creando nuevo archivo.");
            return; 
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            actividades = (List<Actividad>) ois.readObject();
            System.out.println("Actividades cargadas desde " + file.getAbsolutePath());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


