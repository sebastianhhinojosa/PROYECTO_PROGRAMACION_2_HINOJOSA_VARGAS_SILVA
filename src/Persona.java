public class Persona {

    protected int id;
    protected String nombre;
    protected int edad;
    protected String genero;
    protected String ubicacion;
    protected String estadoSalud;

    public Persona(int id, String nombre, int edad, String genero, String ubicacion, String estadoSalud) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (edad < 0 || edad > 100) {
            throw new IllegalArgumentException("Edad fuera de rango razonable.");
        }
        if (!genero.equalsIgnoreCase("Masculino") &&
                !genero.equalsIgnoreCase("Femenino") &&
                !genero.equalsIgnoreCase("Otro")) {
            throw new IllegalArgumentException("Género no válido.");
        }
        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La ubicación no puede estar vacía.");
        }
        if (estadoSalud == null || estadoSalud.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de salud no puede estar vacío.");
        }

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.ubicacion = ubicacion;
        this.estadoSalud = estadoSalud;
    }

    public void actualizarUbicacion(String nuevaUbicacion) {
        if (nuevaUbicacion == null || nuevaUbicacion.trim().isEmpty()) {
            System.out.println("La nueva ubicación no puede estar vacía.");
        } else if (nuevaUbicacion.equalsIgnoreCase(this.ubicacion)) {
            System.out.println("La ubicación ingresada es la misma que la actual.");
        } else {
            this.ubicacion = nuevaUbicacion;
            System.out.println("Ubicación actualizada a: " + nuevaUbicacion);
        }
    }
}
