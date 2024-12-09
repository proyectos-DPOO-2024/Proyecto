import java.io.*;
import java.util.*;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;

public class Deserializar {

    public static void main(String[] args) {
        // Suponiendo que el archivo de serialización es "archivo.ser"
        String archivo = "actividades.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            // Deserializa la lista
            List<Object> listaDeserializada = (List<Object>) ois.readObject();
            
            // Eliminar los elementos null de la lista
            listaDeserializada.removeIf(Objects::isNull);
            
            // Mostrar la lista deserializada sin valores null
            System.out.println("Objeto deserializado (sin null): " + listaDeserializada);
            
            // Aquí puedes recorrer la lista y trabajar con los objetos deserializados
            for (Object obj : listaDeserializada) {
                if (obj instanceof LearningPath) {
                    LearningPath path = (LearningPath) obj;
                    System.out.println("LearningPath: " + path.getTitulo()); // Ejemplo de uso
                }
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

