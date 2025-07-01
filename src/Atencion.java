import java.util.Date;

public class Atencion {
    private final int id;
    private final String tipo;
    private final Date fecha;
    private final Institucion institucion;
    private final String encargadoSeguimiento;   // NUEVO

    public Atencion(int id, String tipo, Date fecha,
                    Institucion institucion, String encargadoSeguimiento) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.institucion = institucion;
        this.encargadoSeguimiento = encargadoSeguimiento;
    }

    public int getId()                     { return id; }
    public String getTipo()                { return tipo; }
    public Date getFecha()                 { return fecha; }
    public Institucion getInstitucion()    { return institucion; }
    public String getEncargadoSeguimiento(){ return encargadoSeguimiento; }

    @Override
    public String toString() {
        return String.format("%d | %s | %tF | %s | Encargado: %s",
                id, tipo, fecha, institucion.getNombre(), encargadoSeguimiento);
    }
}

