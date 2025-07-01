import java.util.Date;

public class Atencion {
    private int id;
    private String tipo;
    private Date fecha;
    private Institucion institucion;
    private String encargadoSeguimiento; // nuevo campo

    public Atencion(int id, String tipo, Date fecha, Institucion institucion, String encargadoSeguimiento) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.institucion = institucion;
        this.encargadoSeguimiento = encargadoSeguimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEncargadoSeguimiento() {
        return encargadoSeguimiento;
    }
}
