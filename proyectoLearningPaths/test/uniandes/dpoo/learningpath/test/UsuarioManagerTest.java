package uniandes.dpoo.learningpath.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.usuarios.Profesor;
import uniandes.dpoo.learningpaths.usuarios.UsuarioManager;

public class UsuarioManagerTest {
    private UsuarioManager usuarioManager;

    @BeforeEach
    public void setUp() {
        usuarioManager = new UsuarioManager(); // Crear una instancia de UsuarioManager
    }

    @Test
    public void testCrearEstudianteGeneraIDUnico() {
        Estudiante estudiante1 = usuarioManager.crearEstudiante("usuario1", "Nombre1", "Apellido1", "contraseña1");
        Estudiante estudiante2 = usuarioManager.crearEstudiante("usuario2", "Nombre2", "Apellido2", "contraseña2");

        // Comprobar que los IDs son únicos
        assertNotEquals(estudiante1.getUsuarioID(), estudiante2.getUsuarioID());
    }

    @Test
    public void testCrearProfesorGeneraIDUnico() {
        Profesor profesor1 = usuarioManager.crearProfesor("usuario1", "Nombre1", "Apellido1", "contraseña1");
        Profesor profesor2 = usuarioManager.crearProfesor("usuario2", "Nombre2", "Apellido2", "contraseña2");

        // Comprobar que los IDs son únicos
        assertNotEquals(profesor1.getUsuarioID(), profesor2.getUsuarioID());
    }

    @Test
    public void testGenerarIDIncrementaCorrectamente() {
        // Crear un usuario para generar el primer ID
        Estudiante estudiante = usuarioManager.crearEstudiante("usuario1", "Nombre1", "Apellido1", "contraseña1");
        
        // Al crear el segundo usuario, el ID debe ser 2
        Estudiante estudiante2 = usuarioManager.crearEstudiante("usuario2", "Nombre2", "Apellido2", "contraseña2");
        
        assertEquals("2", estudiante2.getUsuarioID());
    }
}

