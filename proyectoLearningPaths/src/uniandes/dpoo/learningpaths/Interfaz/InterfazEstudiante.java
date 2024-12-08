package uniandes.dpoo.learningpaths.Interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaActividades;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaResenia;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaUsuarios;
import uniandes.dpoo.learningpaths.usuarios.Estudiante;
import uniandes.dpoo.learningpaths.usuarios.Profesor;
import uniandes.dpoo.learningpaths.usuarios.Usuario;
import uniandes.dpoo.learningpaths.learninghpaths.CatalogoLearningPaths;
import uniandes.dpoo.learningpaths.persistencias.PersistenciaResenia;

public class InterfazEstudiante {
	
	private CatalogoLearningPaths catalogoLearningPath;
	private Estudiante estudiante;
	private PersistenciaLearningPaths persistenciaLearningPaths;
	
	public InterfazEstudiante(Estudiante estudiante, CatalogoLearningPath catalogo,
            PersistenciaLearningPaths persistenciaLearningPaths,
            PersistenciaResenias persistenciaResenias, Scanner scanner) {
		this.estudiante = estudiante;
        this.catalogoLearningPath = catalogo;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaResenias = persistenciaResenias;
		
		setTitle("Learning Path - Estudiante");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        JLabel titleLabel = new JLabel("Bienvenido, Estudiante", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        String[] opciones = {
                "Inscribirse en Learning Path",
                "Ver Learning Paths inscritos",
                "Marcar Learning Path como completado",
                "Crear reseña",
                "Ver actividades de un Learning Path",
                "Ver progreso",
                "Volver al menú principal"
        };
        
        for (String opcion : opciones) {
        	String opcion = opciones[i];
            JButton button = new JButton(opcion);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
            
            int opcionIndex = i + 1;
            button.addActionListener(e -> manejarOpcion(opcionIndex));
        }
        
        DefaultListModel<String> learningPathsModel = new DefaultListModel<>();
        for (String path : catalogoLearningPath.getLearningPaths()) {
            learningPathsModel.addElement(path);
        }
        
        JList<String> learningPathsList = new JList<>(learningPathsModel);
        JScrollPane scrollPane = new JScrollPane(learningPathsList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Caminos de Aprendizaje"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPath = learningPathsList.getSelectedValue();
                if (selectedPath != null) {
                    JOptionPane.showMessageDialog(StudentGUI.this,
                            "Iniciando el camino: " + selectedPath,
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(StudentGUI.this,
                            "Por favor, selecciona un camino para continuar.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGUI gui = new StudentGUI();
            gui.setVisible(true);
        });
    }
}
