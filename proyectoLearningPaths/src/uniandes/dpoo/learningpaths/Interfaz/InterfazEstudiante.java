package uniandes.dpoo.learningpaths.Interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	private Usuario usuario;
	
	public InterfazEstudiante(Estudiante estudiante, CatalogoLearningPath catalogo,
            PersistenciaLearningPaths persistenciaLearningPaths,
            PersistenciaResenias persistenciaResenias, Usuario usuario) {
		this.estudiante = estudiante;
        this.catalogoLearningPath = catalogo;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaResenias = persistenciaResenias;
        this.usuario = usuario;
		
        setTitle("Interfaz Estudiante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(6, 1, 10, 10));
        setContentPane(contentPane);

        agregarBotones();
	}
	
	 private void agregarBotones() {
		 JButton btnInscribirLearningPath = new JButton("Inscribir Learning Path");
		 btnInscribirLearningPath.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 inscribirLearningPath();
			 }
		 });
		 contentPane.add(btnInscribirLearningPath);
		 
		 Jbutton btnVerLearningPaths = new JButton("Ver Learning Paths inscritos");
		 btnVerLearningPaths.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 verLearningPaths();
			 }
		 });
		 contentPane.add(btnVerLearningPaths);
		 
		 JButton btnVerActividades = new JButton("Ver actividades de un Learning Path");
		 btnVerActividades.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 verActividades();
			 }
		 });
		 contentPane.add(btnVerActividades);
		 
		 JButton btnMarcarActividad = new JButton("Marcar actividad como iniciada/completa");
		 btnMarcarActividad.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 marcarActividad();
			 }
		 });
		 contentPane.add(btnMarcarActividad);
		 
		 JButton btnMarcarLearningPath = new JButton("Marcar Learning Path como completado");
		 btnMarcarLearningPath.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 marcarLearningPath();
			 }
		 });
		 contentPane.add(btnMarcarLearningPath);
		 
		 JButton btnCrearResenia = new JButton("Crear reseña");
		 btnMarcarResenia.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 crearResenia();
			 }
		 });
		 contentPane.add(btnCrearResenia);
		 
		 JButton btnVerProgreso = new JButton("Ver Progreso");
		 btnVerProgreso.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 verProgreso();
			 }
		 });
		 contentPane.add(btnVerProgreso);
		 
		 JButton btnVerNotas = new JButton("Ver feedback y notas de actividades");
		 btnVerNotas.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 verNotas();
			 }
		 });
		 contentPane.add(btnVerNotas);
	 }
	 
	private void inscribirLearningPath() {
		String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
		estudiante.inscribirLearningPath(persistenciaLearningPaths, titulo);
	}
	
	private void verLearningPaths() {
		List<LearningPath> learningPathsInscritos = estudiante.getLearningPathsInscritos();
		JFrame frame = new JFrame("Lista de LearningPaths");
        frame.setSize(400, 300);
        
        JList<String> lista = new JList<>(learningPathsInscritos);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lista);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
	}
	
	private void verActividades() {
		String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
		estudiante.verActividadesLearningPath(titulo);
	}
	
	private void marcarActividad() {
		String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
		Scanner scanner = new scanner;
		estudiante.marcarActividadComoCompleta(scanner, persistenciaLearningPaths, titulo);
	}
	
	private void marcarLearningPath() {
		String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
		estudiante.marcarLearningPathCompletado(titulo);
	}
	
	private void crearResenia() {
		estudiante.crearResenia(persistenciaLearningPaths, persistenciaResenias);
	}
	
	private void verProgreso() {
		int opcion = JOptionPane.showInputDialog(this, "Ingrese el progreso que busca (1 para Actividades, 2 para Learning Path):");
		if (opcion < 1 || opcion > 2) {
			JOptionPane.showMessageDialog(this, "opcion invalida");
		} else if (opcion == 1) {
			estudiante.mostrarProgresoActividades();
		} else if (opcion == 2) {
			usuario.mostrarProgreso();	
		}
	}
	
	private void verNotas() {
		estudiante.verFeedback();
	}
	
	
	public static void main(String[] args) {
		CatalogoLearningPath catalogo = new CatalogoLearningPath(learningPaths);

        Estudiante estudiante = new Estudiante();
        PersistenciaLearningPaths persistenciaLearningPaths = new PersistenciaLearningPaths();
        PersistenciaResenias persistenciaResenias = new PersistenciaResenias();

        // Lanzar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            StudentGUI gui = new StudentGUI(estudiante, catalogo, persistenciaLearningPaths, persistenciaResenias);
            gui.setVisible(true);
        });
    }
}
