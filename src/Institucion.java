public class Institucion {
    private final int id;
    private final String nombre;
    private final String tipo;
    private final String contacto;

    public Institucion(int id, String nombre, String tipo, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contacto = contacto;
    }

    public int getId()          { return id; }
    public String getNombre()   { return nombre; }
    public String getTipo()     { return tipo; }
    public String getContacto() { return contacto; }

    @Override
    public String toString() { return id + " - " + nombre + " (" + tipo + ")"; }
}