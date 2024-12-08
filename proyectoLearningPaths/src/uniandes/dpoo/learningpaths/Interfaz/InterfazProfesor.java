package uniandes.dpoo.learningpaths.Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.usuarios.Profesor;

public class InterfazProfesor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Profesor profesor;
    private PersistenciaLearningPaths persistenciaLearningPaths;
    private PersistenciaUsuarios persistenciaUsuarios;

    public InterfazProfesor(Profesor profesor, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaUsuarios persistenciaUsuarios) {
        this.profesor = profesor;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaUsuarios = persistenciaUsuarios;

        setTitle("Interfaz Profesor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(6, 1, 10, 10));
        setContentPane(contentPane);

        agregarBotones();
    }

    private void agregarBotones() {
        JButton btnCrearLearningPath = new JButton("Crear Learning Path");
        btnCrearLearningPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearLearningPath();
            }
        });
        contentPane.add(btnCrearLearningPath);

        JButton btnVerLearningPaths = new JButton("Ver Learning Paths");
        btnVerLearningPaths.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verLearningPaths();
            }
        });
        contentPane.add(btnVerLearningPaths);

        JButton btnCrearActividad = new JButton("Crear Actividad");
        btnCrearActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearActividad();
            }
        });
        contentPane.add(btnCrearActividad);

        JButton btnAgregarActividadALP = new JButton("Agregar Actividad a Learning Path");
        btnAgregarActividadALP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarActividadALearningPath();
            }
        });
        contentPane.add(btnAgregarActividadALP);

        JButton btnEditarLearningPath = new JButton("Editar Learning Path");
        btnEditarLearningPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarLearningPath();
            }
        });
        contentPane.add(btnEditarLearningPath);

        JButton btnCalificarActividades = new JButton("Calificar Actividades");
        btnCalificarActividades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calificarActividades();
            }
        });
        contentPane.add(btnCalificarActividades);
    }

    private void crearLearningPath() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción del Learning Path:");
        String duracionStr = JOptionPane.showInputDialog(this, "Ingrese la duración (en horas):");
        String dificultad = JOptionPane.showInputDialog(this, "Ingrese el nivel de dificultad:");
        String ratingStr = JOptionPane.showInputDialog(this, "Ingrese el rating del Learning Path:");

        try {
            int duracion = Integer.parseInt(duracionStr);
            double rating = Double.parseDouble(ratingStr);

            profesor.crearLearningPath(titulo, descripcion, duracion, dificultad, rating, null);
            persistenciaLearningPaths.guardarLearningPath(null); // Guardar en persistencia
            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }

    private void verLearningPaths() {
        StringBuilder sb = new StringBuilder();
        persistenciaLearningPaths.obtenerLearningPaths().forEach(lp -> {
            sb.append("Título: ").append(lp.getTitulo()).append("\n");
        });
        JOptionPane.showMessageDialog(this, sb.toString(), "Learning Paths", JOptionPane.INFORMATION_MESSAGE);
    }

    private void crearActividad() {
        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de actividad:");
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título:");
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción:");
        String objetivo = JOptionPane.showInputDialog(this, "Ingrese el objetivo:");
        String dificultad = JOptionPane.showInputDialog(this, "Ingrese el nivel de dificultad:");
        String duracionStr = JOptionPane.showInputDialog(this, "Ingrese la duración (en minutos):");
        String calificacionStr = JOptionPane.showInputDialog(this, "Ingrese la calificación:");

        try {
            int duracion = Integer.parseInt(duracionStr);
            float calificacion = Float.parseFloat(calificacionStr);

            profesor.crearActividad(tipo, titulo, descripcion, objetivo, dificultad, duracion, calificacion, null);
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }

    private void agregarActividadALearningPath() {
        String tituloLP = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        String tituloActividad = JOptionPane.showInputDialog(this, "Ingrese el título de la actividad:");

        profesor.agregarActLearningPath(tituloLP, tituloActividad);
        JOptionPane.showMessageDialog(this, "Actividad agregada exitosamente.");
    }

    private void editarLearningPath() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        String nuevoTitulo = JOptionPane.showInputDialog(this, "Ingrese el nuevo título:");
        String nuevaDescripcion = JOptionPane.showInputDialog(this, "Ingrese la nueva descripción:");
        String duracionStr = JOptionPane.showInputDialog(this, "Ingrese la nueva duración (en horas):");
        String nuevaDificultad = JOptionPane.showInputDialog(this, "Ingrese el nuevo nivel de dificultad:");
        String nuevoRatingStr = JOptionPane.showInputDialog(this, "Ingrese el nuevo rating:");

        try {
            int nuevaDuracion = Integer.parseInt(duracionStr);
            double nuevoRating = Double.parseDouble(nuevoRatingStr);

            profesor.editarLearningPath(titulo, nuevoTitulo, nuevaDescripcion, nuevaDuracion, nuevaDificultad, nuevoRating);
            JOptionPane.showMessageDialog(this, "Learning Path editado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }

    private void calificarActividades() {
        String usuarioEstudiante = JOptionPane.showInputDialog(this, "Ingrese el nombre del estudiante:");
        String tituloLP = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        String tituloActividad = JOptionPane.showInputDialog(this, "Ingrese el título de la actividad:");
        String calificacionStr = JOptionPane.showInputDialog(this, "Ingrese la calificación:");

        try {
            float calificacion = Float.parseFloat(calificacionStr);

            // Implementar lógica para calificar actividades
            JOptionPane.showMessageDialog(this, "Actividad calificada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en la calificación.");
        }
    }
    

}
