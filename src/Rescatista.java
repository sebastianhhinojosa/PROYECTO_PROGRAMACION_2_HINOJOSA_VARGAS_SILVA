public class Rescatista extends Persona {
    private final String idInstitucional;
    private final Institucion institucion;

    public Rescatista(int id, String nombre, int edad, String genero,
                      String ubicacion, String estadoSalud,
                      String idInstitucional, Institucion institucion) {

        super(id, nombre, edad, genero, ubicacion, estadoSalud);

        if (idInstitucional == null || idInstitucional.isBlank()) {
            System.out.println("⚠ Advertencia: ID institucional vacío.");
            this.idInstitucional = "N/A";
        } else this.idInstitucional = idInstitucional;

        this.institucion = institucion;
    }

    public String     getIdInstitucional() { return idInstitucional; }
    public Institucion getInstitucion()    { return institucion; }

    @Override
    public String toString() {
        return String.format("Rescatista %s (%s) - Institución: %s",
                nombre, idInstitucional, institucion.getNombre());
    }
}