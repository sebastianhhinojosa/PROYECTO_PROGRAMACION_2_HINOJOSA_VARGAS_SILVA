public class Institucion {
    private int id;
    private String nombre;
    private String tipo;
    private String contacto;

    public Institucion(int id, String nombre, String tipo, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contacto = contacto;
    }
    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }

}
