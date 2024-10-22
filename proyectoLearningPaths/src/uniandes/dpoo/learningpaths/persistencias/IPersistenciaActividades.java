package uniandes.dpoo.learningpaths.persistencias;

import java.io.IOException;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;

public interface IPersistenciaActividades {

	public void cargarActividades( String archivo, Actividad actividad ) throws IOException, InformacionInconsistenteException;
	 
	public void salvarActividades( String archivo, Actividad actividad ) throws IOException;
}
