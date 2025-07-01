import java.util.*;

public class Main {
    static List<PersonaSituacionCalle> personas = new ArrayList<>();
    static List<Institucion> instituciones = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE REGISTRO DE PERSONAS EN SITUACIÓN DE CALLE ---");
            System.out.println("1. Registro de personas (R1)");
            System.out.println("2. Registro de atención (R2)");
            System.out.println("3. Gestión de instituciones (R3)");
            System.out.println("4. Reportes y estadísticas (R4)");
            System.out.println("5. Localización de casos (R5)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer
// menu

            switch (opcion) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    registrarAtencion();
                    break;
                case 3:
                    registrarInstitucion();
                    break;
                case 4:
                    generarReporte();
                    break;
                case 5:
                    mostrarLocalizaciones();
                    break;
                case 6:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    // R1: Registro de personas
    private static void registrarPersona() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Género: ");
        String genero = sc.nextLine();
        System.out.print("Ubicación actual: ");
        String ubicacion = sc.nextLine();
        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        PersonaSituacionCalle persona = new PersonaSituacionCalle(
                personas.size() + 1, nombre, edad, genero, ubicacion, estadoSalud, new Date()
        );
        personas.add(persona);
        System.out.println(" Persona registrada con ID: " + persona.id);
    }

    // R2: Registro de atención
    private static void registrarAtencion() {
        if (personas.isEmpty() || instituciones.isEmpty()) {
            System.out.println("Debe haber al menos una persona y una institución registrada.");
            return;
        }

        System.out.print("ID de persona: ");
        int idPersona = sc.nextInt(); sc.nextLine();
        PersonaSituacionCalle persona = buscarPersona(idPersona);
        if (persona == null) {
            System.out.println(" Persona no encontrada.");
            return;
        }

        System.out.print("Tipo de atención brindada: ");
        String tipo = sc.nextLine();
        System.out.print("ID de institución que ayudó: ");
        int idInst = sc.nextInt(); sc.nextLine();
        Institucion inst = buscarInstitucion(idInst);
        if (inst == null) {
            System.out.println(" Institución no encontrada.");
            return;
        }

        Atencion atencion = new Atencion(persona.getHistorial().size() + 1, tipo, new Date(), inst);
        persona.agregarAtencion(atencion);
        System.out.println(" Atención registrada correctamente.");
    }

    // R3: Gestión de instituciones
    private static void registrarInstitucion() {
        System.out.print("Nombre de la institución: ");
        String nombre = sc.nextLine();
        System.out.print("Tipo (ONG, Gubernamental, etc.): ");
        String tipo = sc.nextLine();
        System.out.print("Contacto: ");
        String contacto = sc.nextLine();

        Institucion inst = new Institucion(instituciones.size() + 1, nombre, tipo, contacto);
        instituciones.add(inst);
        System.out.println("✔ Institución registrada con ID: " + inst.getId());
    }

    // R4: Reportes y estadísticas
    private static void generarReporte() {
        System.out.println("\n--- Reporte de personas registradas ---");
        for (PersonaSituacionCalle p : personas) {
            System.out.println("ID: " + p.id + " | Nombre: " + p.nombre + " | Atenciones: " + p.getHistorial().size());
        }
        System.out.println("Total de personas: " + personas.size());
        System.out.println("Total de instituciones: " + instituciones.size());
    }

    // R5: Localización de casos (texto simulado)
    private static void mostrarLocalizaciones() {
        System.out.println("\n--- Localización de casos activos ---");
        for (PersonaSituacionCalle p : personas) {
            System.out.println("Persona: " + p.nombre + " | Ubicación: " + p.ubicacion);
        }
    }

    // Métodos auxiliares
    private static PersonaSituacionCalle buscarPersona(int id) {
        return personas.stream().filter(p -> p.id == id).findFirst().orElse(null);
    }

    private static Institucion buscarInstitucion(int id) {
        return instituciones.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
