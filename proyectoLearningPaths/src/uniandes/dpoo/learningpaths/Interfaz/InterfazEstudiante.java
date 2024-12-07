package uniandes.dpoo.learningpaths.Interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

public class InterfazEstudiante {
	
	private CatalogoLearningPaths catalogoLearningPath;
	
	public InterfazEstudiante() {
		this.catalogoLearningPath = catalogo;
		
		setTitle("Learning Path - Estudiante");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        JLabel titleLabel = new JLabel("Bienvenido, Estudiante", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
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
                    // Aquí puedes invocar lógica para manejar la selección
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
