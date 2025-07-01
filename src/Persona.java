public class Persona {
    protected final int id;
    protected String nombre;
    protected int edad;
    protected String genero;
    protected String ubicacion;
    protected String estadoSalud;

    public Persona(int id, String nombre, int edad, String genero,
                   String ubicacion, String estadoSalud) {

        if (id <= 0)                          throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isBlank())      throw new IllegalArgumentException("Nombre vacío.");
        if (edad < 0 || edad > 120)                  throw new IllegalArgumentException("Edad fuera de rango.");
        if (!genero.equalsIgnoreCase("Masculino") &&
                !genero.equalsIgnoreCase("Femenino") &&
                !genero.equalsIgnoreCase("Otro"))        throw new IllegalArgumentException("Género no válido.");
        if (ubicacion == null || ubicacion.isBlank())    throw new IllegalArgumentException("Ubicación vacía.");
        if (estadoSalud == null || estadoSalud.isBlank()) throw new IllegalArgumentException("Estado de salud vacío.");

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.ubicacion = ubicacion;
        this.estadoSalud = estadoSalud;
    }

    public void actualizarUbicacion(String nuevaUbicacion) {
        if (nuevaUbicacion == null || nuevaUbicacion.isBlank()) {
            System.out.println("La nueva ubicación no puede estar vacía.");
        } else if (nuevaUbicacion.equalsIgnoreCase(this.ubicacion)) {
            System.out.println("La ubicación ingresada es la misma que la actual.");
        } else {
            this.ubicacion = nuevaUbicacion;
            System.out.println("Ubicación actualizada a: " + nuevaUbicacion);
        }
    }
}
