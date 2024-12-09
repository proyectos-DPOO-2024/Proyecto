package uniandes.dpoo.learningpaths.Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.usuarios.Profesor;
import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;

public class InterfazProfesor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Profesor profesor;
    private PersistenciaLearningPaths persistenciaLearningPaths;
    private PersistenciaUsuarios persistenciaUsuarios;
    private List<double> promedios;
    private List<LocalDate> fechas;

    public InterfazProfesor(Profesor profesor, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaUsuarios persistenciaUsuarios) {
        this.profesor = profesor;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaUsuarios = persistenciaUsuarios;
        this.promedios = new ArrayList<double>();
        this.fechas = new ArrayList<LocalDate>();

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
        
        JButton btnVerProgresoEstudiantes = new JButton("Ver progreso de los estudiantes");
        btnVerProgresoEstudiantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verProgresoEstudiantes();
            }
        });
        contentPane.add(btnVerProgresoEstudiantes);
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
    
    private void verProgresoEstudiantes() {
    	JFrame frame = new JFrame("Visualización de Actividades Realizadas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        
    	JPanel panelActividades = new JPanel();
        panelActividades.setLayout(new GridLayout(12, 31, 2, 2));
        
    	int totalProgreso = 0;
    	List<Actividad> actividades = learningPath.getActividades();
    	long actividadesCompletadas = actividades.stream().filter(Actividad::getCompletada).count();
    	long totalActividades = actividades.size();
    	
    	String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
    	LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
                .filter(lp -> lp.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
    	if (learningPath != null) {
    		List<Estudiante> estudiantes = learningPath.getEstudiantes();
    		long totalEstudiantes = estudiantes.size();
    		for (i = 0; i < estudiantes.size(), i++) {
    			if (totalActividades > 0) {
                    double porcentajeCompletado = (double) actividadesCompletadas / totalActividades * 100;
    			} else {
    				porcentajeCompletado = 0;
    			}
    			Estudiante estudiante = estudiantes.get(i);
    			totalProgreso = totalProgreso + porcentajeCompletado;
    		}
    		double promedioProgreso = (double) totalProgreso / totalEstudiantes;
    		promedios.add(promedioProgreso);
    		LocalDate fechaActual = LocalDate.now();
    		fechas.add(fechaActual);
    		for (i = 0; i < promedios.size(), i++) {
    			JPanel celda = new JPanel();
    			int valor = promedios.get(i);
    			celda.setBackground(getColorPorActividad(valor));
    			panelActividades.add(celda);
    		}
    		
    		frame.add(panelActividades);
    	    frame.setVisible(true);
    		
    	}
    }
    
    private Color getColorPorActividad(int valor) {
        if (valor == 0) return Color.LIGHT_GRAY;
        if (valor < 5) return new Color(173, 216, 230);
        if (valor < 10) return new Color(100, 149, 237);
        return new Color(0, 0, 139);
    }
}
