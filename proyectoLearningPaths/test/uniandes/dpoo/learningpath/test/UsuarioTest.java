package uniandes.dpoo.learningpath.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testGettersAndSetters() {
        Usuario usuario = new Estudiante("1", "usuario1", "Nombre", "Apellido", "contraseña");
        assertEquals("1", usuario.getUsuarioID());
        assertEquals("usuario1", usuario.getNombreUsuario());
        assertEquals("Nombre", usuario.getNombre());
        assertEquals("Apellido", usuario.getApellido());
        
        usuario.setContraseña("nuevaContraseña");
        assertEquals("nuevaContraseña", usuario.getContraseña());
    }
}

