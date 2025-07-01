public class Persona {
    protected final int id;
    protected String nombre;
    protected int edad;
    protected String genero;
    protected String ubicacion;
    protected String estadoSalud;

    public Persona(int id, String nombre, int edad, String genero,
                   String ubicacion, String estadoSalud) {

        this.id = (id > 0) ? id : 0;
        if (id <= 0) {
            System.out.println(" Advertencia: ID inválido, se estableció como 0.");
        }

        if (nombre == null || nombre.isBlank()) {
            System.out.println(" Advertencia: Nombre vacío.");
            this.nombre = "Sin nombre";
        } else {
            this.nombre = nombre;
        }

        if (edad < 0 || edad > 120) {
            System.out.println(" Advertencia: Edad fuera de rango.");
            this.edad = 0;
        } else {
            this.edad = edad;
        }

        if (genero == null ||
                (!genero.equalsIgnoreCase("Masculino") &&
                        !genero.equalsIgnoreCase("Femenino") &&
                        !genero.equalsIgnoreCase("Otro"))) {
            System.out.println("⚠ Advertencia: Género no válido. Se usará 'Otro'.");
            this.genero = "Otro";
        } else {
            this.genero = genero;
        }

        if (ubicacion == null || ubicacion.isBlank()) {
            System.out.println(" Advertencia: Ubicación vacía.");
            this.ubicacion = "Desconocida";
        } else {
            this.ubicacion = ubicacion;
        }

        if (estadoSalud == null || estadoSalud.isBlank()) {
            System.out.println(" Advertencia: Estado de salud vacío.");
            this.estadoSalud = "No especificado";
        } else {
            this.estadoSalud = estadoSalud;
        }
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