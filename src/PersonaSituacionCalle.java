import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaSituacionCalle extends Persona {
    private Date fechaDeteccion;
    private List<Atencion> historial;

    public PersonaSituacionCalle(int id, String nombre, int edad, String genero, String ubicacion, String estadoSalud, Date fechaDeteccion){
        super(id, nombre, edad, genero, ubicacion,estadoSalud);
        this.fechaDeteccion = fechaDeteccion;
        this.historial = new ArrayList<>();
    }

    public void agregarAtencion(Atencion atencion){
        historial.add(atencion);
    }
    public List<Atencion> getHistorial(){
        return historial;
    }

}
