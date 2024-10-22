package uniandes.dpoo.learningpaths.persistencias;

import java.io.IOException;

import uniandes.dpoo.learningpaths.excepciones.InformacionInconsistenteException;
import uniandes.dpoo.learningpaths.usuarios.Usuario;

public interface IPersistenciaUsuarios {

	 public void cargarUsuario( String archivo, Usuario usuario ) throws IOException, InformacionInconsistenteException;
	 
	 public void salvarUsuario( String archivo, Usuario usuario ) throws IOException;
}
