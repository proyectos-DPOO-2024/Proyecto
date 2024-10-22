package uniandes.dpoo.learningpaths.persistencias;

import java.io.IOException;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;

public interface IPersistenciaLeaningPaths {
	
	public void cargarLearningPath( String archivo, LearningPath learningpath ) throws IOException, InformacionInconsistenteException;
	 
	public void salvarLearningPath( String archivo, LearningPath learningpath ) throws IOException;

}
