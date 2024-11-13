package uniandes.dpoo.learningpaths.usuarios;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpaths.learninghpaths.LearningPath;
public abstract class Usuario {

    private String usuarioID;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String contraseña;
    private String tipoUsuario;
    private List<LearningPath> learningPathsInscritos;
    private List<Boolean> learningPathsCompletados;

    public Usuario(String usuarioID, String nombreUsuario, String nombre, String apellido, String contraseña, String tipoUsuario) {
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.learningPathsInscritos = new ArrayList<>();
        this.learningPathsCompletados = new ArrayList<>();
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<LearningPath> getLearningPathsInscritos() {
        return learningPathsInscritos;
    }

    public void inscribirLearningPath(LearningPath learningPath) {
        if (learningPathsInscritos.size() < 6) {
            this.learningPathsInscritos.add(learningPath);
            this.learningPathsCompletados.add(false);
        } else {
            System.out.println("No puedes inscribirte en más de 6 Learning Paths.");
        }
    }

    public void marcarLearningPathCompletado(String titulo) {
        for (int i = 0; i < learningPathsInscritos.size(); i++) {
            if (learningPathsInscritos.get(i).getTitulo().equals(titulo)) {
                learningPathsCompletados.set(i, true);
                System.out.println("Learning Path marcado como completado.");
                return;
            }
        }
        System.out.println("Learning Path no encontrado.");
    }

    public void mostrarLearningPathsInscritos() {
        if (learningPathsInscritos.isEmpty()) {
            System.out.println("No estás inscrito en ningún Learning Path.");
        } else {
            System.out.println("Learning Paths inscritos:");
            for (int i = 0; i < learningPathsInscritos.size(); i++) {
                LearningPath learningPath = learningPathsInscritos.get(i);
                String estado = learningPathsCompletados.get(i) ? "Completado" : "No completado";
                System.out.println("- " + learningPath.getTitulo() + " (" + estado + ")");
            }
        }
    }

    public void mostrarProgreso() {
        int completados = 0;
        for (boolean completado : learningPathsCompletados) {
            if (completado) {
                completados++;
            }
        }
        double progreso = (double) completados / learningPathsInscritos.size() * 100;
        System.out.println("Progreso: " + progreso + "%");
    }
}
