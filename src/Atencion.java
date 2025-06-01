import java.util.Date;
public class Atencion {
    private int id;
    private String tipo;
    private Date fecha;
    private Institucion institucion;

    public Atencion(int id, String tipo, Date fecha, Institucion institucion){
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.institucion = institucion;
    }
    public String getTipo(){
        return tipo;
    }
    public Institucion getInstitucion(){
        return institucion;
    }
    public Date getFecha(){
        return fecha;
    }

}
