package uniandes.dpoo.learningpaths.Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Actividad;
import uniandes.dpoo.learningpaths.learninghpaths.Actividad.Reseña;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.usuarios.Profesor;

public class InterfazProfesor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Profesor profesor;
    private PersistenciaLearningPaths persistenciaLearningPaths;
    private PersistenciaActividades persistenciaatividades;

    public InterfazProfesor(Profesor profesor, PersistenciaLearningPaths persistenciaLearningPaths, PersistenciaUsuarios persistenciaUsuarios, PersistenciaActividades persistenciaatividades) {
        this.profesor = profesor;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaatividades = persistenciaatividades;
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
        
        JButton btnVerCatalogoActividades = new JButton("Ver el catalogó de actividades");
        btnVerCatalogoActividades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	verCatalogoActividades();
            }
        });
        contentPane.add(btnVerCatalogoActividades);

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
        if (titulo == null || titulo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
            return;
        }

        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción del Learning Path:");
        if (descripcion == null || descripcion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.");
            return;
        }

        String duracionStr = JOptionPane.showInputDialog(this, "Ingrese la duración (en horas):");
        if (duracionStr == null || duracionStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La duración no puede estar vacía.");
            return;
        }

        String dificultad = JOptionPane.showInputDialog(this, "Ingrese el nivel de dificultad:");
        if (dificultad == null || dificultad.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nivel de dificultad no puede estar vacío.");
            return;
        }

        String ratingStr = JOptionPane.showInputDialog(this, "Ingrese el rating del Learning Path:");
        if (ratingStr == null || ratingStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El rating no puede estar vacío.");
            return;
        }

        try {
            int duracion = Integer.parseInt(duracionStr);
            double rating = Double.parseDouble(ratingStr);

            // Crear el Learning Path
            LearningPath learningPath = profesor.crearLearningPath(titulo, descripcion, duracion, dificultad, rating, null);
            
            // Guardar en persistencia
            persistenciaLearningPaths.guardarLearningPath(learningPath); // Ahora se guarda el objeto creado

            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Por favor, ingrese valores válidos.");
        }
    }


    private void verLearningPaths() {
        try {
            // Obtén los Learning Paths desde la persistencia
            ArrayList<LearningPath> learningPaths = persistenciaLearningPaths.obtenerLearningPaths();

            // Verifica si la lista está vacía
            if (learningPaths == null || learningPaths.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay Learning Paths disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Filtra nulos y obtiene títulos de los Learning Paths
            String[] opciones = learningPaths.stream()
                    .filter(lp -> lp != null) // Asegúrate de filtrar los nulos
                    .map(LearningPath::getTitulo) // Obtén los títulos de los Learning Paths
                    .toArray(String[]::new);

            // Si la lista está vacía después de filtrar nulos, muestra un mensaje
            if (opciones.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay Learning Paths válidos disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Muestra un cuadro de diálogo con los títulos de los Learning Paths
            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione un Learning Path:",
                    "Learning Paths Disponibles",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            // Si se selecciona un título, busca y muestra los detalles del Learning Path correspondiente
            if (seleccion != null) {
                LearningPath seleccionado = learningPaths.stream()
                        .filter(lp -> seleccion.equals(lp.getTitulo())) // Encuentra el LearningPath seleccionado
                        .findFirst()
                        .orElse(null); // Si no lo encuentra, retorna null

                // Verifica si el objeto seleccionado es nulo antes de acceder a sus atributos
                if (seleccionado != null) {
                    String detalles = "ID: " + seleccionado.getLearningpathID() + "\n" +
                            "Título: " + seleccionado.getTitulo() + "\n" +
                            "Descripción: " + seleccionado.getDescripcion() + "\n" +
                            "Nivel de Dificultad: " + seleccionado.getNivelDificultad() + "\n" +
                            "Duración: " + seleccionado.getDuracion() + " horas\n" +
                            "Calificación: " + seleccionado.getRating() + " estrellas\n" +
                            "Número de Actividades: " + seleccionado.getActividades().size() + "\n" +
                            "Número de Reseñas: " + seleccionado.getResenias().size();

                    JOptionPane.showMessageDialog(this, detalles, "Detalles del Learning Path", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo encontrar el Learning Path seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar los Learning Paths: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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

            Actividad actividad = profesor.crearActividad(tipo, titulo, descripcion, objetivo, dificultad, duracion, calificacion, null);
            persistenciaatividades.guardarActividad(actividad);
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }
    
    private void verCatalogoActividades() {
        try {
            // Obtener actividades desde la persistencia
            ArrayList<Actividad> actividades = (ArrayList<Actividad>) persistenciaatividades.obtenerActividades();

            // Verifica si hay actividades disponibles
            if (actividades == null || actividades.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay actividades disponibles.", "Catálogo de Actividades", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Construir una lista con los títulos de las actividades
            String[] opciones = actividades.stream()
                    .map(Actividad::getTitulo) // Mapeamos a los títulos de las actividades
                    .toArray(String[]::new);

            // Muestra las opciones al usuario
            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione una actividad para ver más detalles:",
                    "Catálogo de Actividades",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            // Mostrar detalles si hay selección
            if (seleccion != null) {
                Actividad seleccionada = actividades.stream()
                        .filter(a -> seleccion.equals(a.getTitulo()))
                        .findFirst()
                        .orElse(null);

                if (seleccionada != null) {
                    String detalles = "Título: " + seleccionada.getTitulo() + "\n" +
                                      "Descripción: " + seleccionada.getDescripcion() + "\n" +
                                      "Nivel de Dificultad: " + seleccionada.getNivelDificultad() + "\n" +
                                      "Duración: " + seleccionada.getDuracion() + " minutos\n" +
                                      "Calificación: " + seleccionada.getCalificacion() + "\n";

                    JOptionPane.showMessageDialog(this, detalles, "Detalles de la Actividad", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la actividad seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar las actividades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void agregarActividadALearningPath() {
        String tituloLP = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        String tituloActividad = JOptionPane.showInputDialog(this, "Ingrese el título de la actividad:");

        // Agregar la actividad al LearningPath usando el profesor
        profesor.agregarActLearningPath(tituloLP, tituloActividad);

        // Obtener el LearningPath actualizado desde la persistencia
        LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
            .filter(lp -> lp.getTitulo().equalsIgnoreCase(tituloLP))
            .findFirst()
            .orElse(null);

        if (learningPath != null) {
            // Actualizar el LearningPath en la lista persistida
            persistenciaLearningPaths.eliminarLearningPath(tituloLP);  // Eliminar el anterior si existe
            persistenciaLearningPaths.guardarLearningPath(learningPath);  // Guardar el actualizado
            JOptionPane.showMessageDialog(this, "Actividad agregada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el Learning Path.");
        }
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

            // Editar el Learning Path usando el profesor
            profesor.editarLearningPath(titulo, nuevoTitulo, nuevaDescripcion, nuevaDuracion, nuevaDificultad, nuevoRating);

            // Obtener el LearningPath actualizado desde la persistencia
            LearningPath learningPath = persistenciaLearningPaths.obtenerLearningPaths().stream()
                .filter(lp -> lp.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

            if (learningPath != null) {
                // Actualizar el LearningPath en la lista persistida
                persistenciaLearningPaths.eliminarLearningPath(titulo);  // Eliminar el anterior si existe
                persistenciaLearningPaths.guardarLearningPath(learningPath);  // Guardar el actualizado
                JOptionPane.showMessageDialog(this, "Learning Path editado y guardado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el Learning Path.");
            }
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