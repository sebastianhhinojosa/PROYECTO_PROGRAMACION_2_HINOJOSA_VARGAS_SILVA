public class Persona {

        protected int id;
        protected String nombre;
        protected int edad;
        protected String genero;
        protected String ubicacion;
        protected String estadoSalud;

        public Persona(int id, String nombre, int edad, String genero, String ubicacion, String estadoSalud) {
            this.id = id;
            this.nombre = nombre;
            this.edad = edad;
            this.genero = genero;
            this.ubicacion = ubicacion;
            this.estadoSalud = estadoSalud;
        }
    public void actualizarUbicacion(String nuevaUbicacion) {
        this.ubicacion = nuevaUbicacion;
    }
}
