package uniandes.dpoo.learningpaths.persistencias;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaResenia {
    
    private List<String> reseñas = new ArrayList<>();

    public void guardarReseña(String reseña) {
        reseñas.add(reseña);
    }

    public List<String> obtenerReseñas() {
        return reseñas;
    }

}