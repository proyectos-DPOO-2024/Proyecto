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

public class InicioSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField password; // Usar JPasswordField para ocultar la contraseña

    // Dependencias necesarias
    private PersistenciaUsuarios persistenciaUsuarios;
    private PersistenciaResenia persistenciaResenias;
    private PersistenciaLearningPaths persistenciaLearningPaths;
    private PersistenciaActividades persistenciaActividades;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Simulación de dependencias
        PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
        PersistenciaResenia persistenciaResenias = new PersistenciaResenia();
        PersistenciaLearningPaths persistenciaLearningPaths = new PersistenciaLearningPaths();
        PersistenciaActividades persistenciaActividades = new PersistenciaActividades();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InicioSesion frame = new InicioSesion(
                        persistenciaUsuarios, 
                        persistenciaResenias, 
                        persistenciaLearningPaths, 
                        persistenciaActividades
                    );
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor con dependencias.
     */
    public InicioSesion(
        PersistenciaUsuarios persistenciaUsuarios,
        PersistenciaResenia persistenciaResenias,
        PersistenciaLearningPaths persistenciaLearningPaths,
        PersistenciaActividades persistenciaActividades
    ) {
        this.persistenciaUsuarios = persistenciaUsuarios;
        this.persistenciaResenias = persistenciaResenias;
        this.persistenciaLearningPaths = persistenciaLearningPaths;
        this.persistenciaActividades = persistenciaActividades;

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        user = new JTextField();
        user.setBounds(149, 63, 195, 30);
        contentPane.add(user);
        user.setColumns(10);

        JLabel labelUsuario = new JLabel("Usuario :");
        labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelUsuario.setBounds(86, 61, 92, 30);
        contentPane.add(labelUsuario);

        password = new JPasswordField();
        password.setBounds(149, 104, 195, 30);
        contentPane.add(password);

        JLabel labelContraseña = new JLabel("Contraseña :");
        labelContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelContraseña.setBounds(59, 102, 92, 30);
        contentPane.add(labelContraseña);

        JButton iniciosesion = new JButton("Iniciar Sesión");
        iniciosesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manejarInicioSesion();
            }
        });
        iniciosesion.setBounds(159, 145, 124, 30);
        contentPane.add(iniciosesion);

        JButton registro = new JButton("Registrarse");
        registro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		manejarRegistro();
        	}
        });
        registro.setBounds(159, 186, 124, 30);
        contentPane.add(registro);
    }

    /**
     * Maneja el inicio de sesión al presionar el botón.
     */
    private void manejarInicioSesion() {
        String nombreUsuario = user.getText();
        String contraseña = new String(password.getPassword()); // Convertir la contraseña a String

        // Lógica de inicio de sesión
        Usuario usuario = persistenciaUsuarios.obtenerUsuarios().stream()
            .filter(u -> u.getNombreUsuario().equals(nombreUsuario) && u.getContraseña().equals(contraseña))
            .findFirst()
            .orElse(null);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            if (usuario instanceof Estudiante) {
                // Lógica para opciones de estudiante
                System.out.println("Opciones para Estudiante.");
            } else if (usuario instanceof Profesor) {
                // Lógica para opciones de profesor
                System.out.println("Opciones para Profesor.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void manejarRegistro() {
        // Crear un nuevo cuadro de diálogo para recopilar los datos del usuario
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS));

        // Campos para el registro
        JTextField nombreUsuarioField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField apellidoField = new JTextField();
        JPasswordField contraseñaField = new JPasswordField();
        String[] opcionesTipoUsuario = {"Estudiante", "Profesor"};
        JComboBox<String> tipoUsuarioComboBox = new JComboBox<>(opcionesTipoUsuario);

        // Añadir campos al panel
        panelRegistro.add(new JLabel("Tipo de usuario:"));
        panelRegistro.add(tipoUsuarioComboBox);
        panelRegistro.add(new JLabel("Nombre de usuario:"));
        panelRegistro.add(nombreUsuarioField);
        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(nombreField);
        panelRegistro.add(new JLabel("Apellido:"));
        panelRegistro.add(apellidoField);
        panelRegistro.add(new JLabel("Contraseña:"));
        panelRegistro.add(contraseñaField);

        int result = JOptionPane.showConfirmDialog(
            this, 
            panelRegistro, 
            "Registro de Usuario", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String tipoUsuario = tipoUsuarioComboBox.getSelectedItem().toString().toLowerCase();
            String nombreUsuario = nombreUsuarioField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (!nombreUsuario.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !contraseña.isEmpty()) {
                if (tipoUsuario.equals("estudiante")) {
                    Estudiante estudiante = new Estudiante(nombreUsuario, nombre, apellido, contraseña);
                    persistenciaUsuarios.guardarUsuario(estudiante);
                    JOptionPane.showMessageDialog(this, "Estudiante registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else if (tipoUsuario.equals("profesor")) {
                    Profesor profesor = new Profesor(nombreUsuario, nombre, apellido, contraseña);
                    persistenciaUsuarios.guardarUsuario(profesor);
                    JOptionPane.showMessageDialog(this, "Profesor registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Tipo de usuario no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
