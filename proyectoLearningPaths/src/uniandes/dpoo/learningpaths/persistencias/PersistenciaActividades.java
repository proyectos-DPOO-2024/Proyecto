package uniandes.dpoo.learningpaths.persistencias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.ActividadQuiz;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Pregunta;

public class PersistenciaActividades {
	
    private List<Actividad> actividades = new ArrayList<>();

    public void guardarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public List<Actividad> obtenerActividades() {
        return actividades;
    }
}


