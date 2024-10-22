package uniandes.dpoo.learningpaths.learninghpaths.Actividad;

public class ActividadTarea extends Actividad{
	private boolean enviada;
    private boolean calificada;
    private boolean exitosa;
    
    public ActividadTarea(String titulo, String descripcion, String objetivo, String nivelDificultad, int duracion, Float calificacion) {
        super(titulo, descripcion, objetivo, nivelDificultad, duracion, calificacion);
        this.enviada = false;
        this.calificada = false;
        this.exitosa = false;
    }
    
    public boolean getEnviada() {
    	return enviada;
    }
    
    public boolean getCalificada() {
    	return calificada;
    }
    
    public boolean getExitosa() {
    	return exitosa;
    }
    
    public void enviarTarea() {
        if (!verificarPrerequisitosCompletados()) {
            System.out.println("Advertencia: No has completado las actividades previas.");
        } else {
        	this.enviada = true;
            System.out.println("Tarea enviada.");
        }
    }
    
    public void calificar(boolean resultado) {
        if (enviada) {
            this.calificada = true;
            this.exitosa = resultado;
            System.out.println("Tarea calificada como " + (exitosa ? "exitosa" : "no exitosa"));
        } else {
            System.out.println("La tarea aún no ha sido enviada.");
        }
    }
    public void realizar() {
        System.out.println("Realizando la tarea...");
        enviarTarea();
    }
    
    public Actividad clonar() {
    	return new ActividadTarea(getTitulo(), getDescripcion(), getObjetivo(), getDificultad(), getDuracion(), getCalificacion());
    }

	@Override
	public void clificar() {
		// TODO Auto-generated method stub
		
	}
}
