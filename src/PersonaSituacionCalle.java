import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaSituacionCalle extends Persona {

    private final Date fechaDeteccion;
    private final List<Atencion> historial = new ArrayList<>();

    public PersonaSituacionCalle(int id, String nombre, int edad, String genero,
                                 String ubicacion, String estadoSalud, Date fechaDeteccion) {
        super(id, nombre, edad, genero, ubicacion, estadoSalud);
        this.fechaDeteccion = fechaDeteccion;
    }

    public void agregarAtencion(Atencion a) { historial.add(a); }
    public List<Atencion> getHistorial()    { return historial; }
    public Date getFechaDeteccion()         { return fechaDeteccion; }
}