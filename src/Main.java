import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<PersonaSituacionCalle> personas      = new ArrayList<>();
    private static final List<Institucion>           instituciones = new ArrayList<>();

    /* ---------- NUEVO: lector seguro de enteros ---------- */
    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: debe ingresar un número entero.");
            }
        }
    }

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("");   // se sobreescribió la lectura insegura
            switch (opcion) {
                case 1 -> registrarPersona();
                case 2 -> registrarAtencion();
                case 3 -> registrarInstitucion();
                case 4 -> generarReporte();
                case 5 -> mostrarLocalizaciones();
                case 6 -> System.out.println("Gracias por usar el sistema.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        sc.close();
    }

    /* ---------- Menú ---------- */
    private static void mostrarMenu() {
        System.out.println("\n--- SISTEMA DE REGISTRO DE PERSONAS EN SITUACIÓN DE CALLE ---");
        System.out.println("1. Registro de personas (R1)");
        System.out.println("2. Registro de atención (R2)");
        System.out.println("3. Gestión de instituciones (R3)");
        System.out.println("4. Reportes y estadísticas (R4)");
        System.out.println("5. Localización de casos (R5)");
        System.out.println("6. Salir");
    }

    /* ---------- R1: Personas ---------- */
    private static void registrarPersona() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        int edad = leerEntero("Edad: ");

        System.out.print("Género (Masculino/Femenino/Otro): ");
        String genero = sc.nextLine();

        System.out.print("Ubicación actual: ");
        String ubicacion = sc.nextLine();

        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        try {
            PersonaSituacionCalle p = new PersonaSituacionCalle(
                    personas.size() + 1, nombre, edad, genero,
                    ubicacion, estadoSalud, new Date()
            );
            personas.add(p);
            System.out.println("✔ Persona registrada con ID: " + p.id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ---------- R2: Atención ---------- */
    private static void registrarAtencion() {

        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        if (instituciones.isEmpty()) {
            System.out.println("No hay instituciones registradas.");
            return;
        }

        int idPersona = leerEntero("ID de persona: ");
        PersonaSituacionCalle persona = buscarPersona(idPersona);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }

        System.out.print("Tipo de atención brindada: ");
        String tipo = sc.nextLine();

        int idInst = leerEntero("ID de institución: ");
        Institucion inst = buscarInstitucion(idInst);
        if (inst == null) {
            System.out.println("Institución no encontrada.");
            return;
        }

        System.out.print("Encargado de seguimiento: ");
        String encargado = sc.nextLine();

        Atencion atencion = new Atencion(
                persona.getHistorial().size() + 1,
                tipo, new Date(), inst, encargado
        );
        persona.agregarAtencion(atencion);

        System.out.println("✔ Atención registrada.");
    }

    /* ---------- R3: Instituciones ---------- */
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

    /* ---------- R4: Reportes ---------- */
    private static void generarReporte() {
        System.out.println("\n--- Reporte general ---");

        System.out.println("Personas registradas: " + personas.size());
        for (PersonaSituacionCalle p : personas) {
            System.out.printf("ID:%d | Nombre:%s | Atenciones:%d%n",
                    p.id, p.nombre, p.getHistorial().size());
        }

        System.out.println("\nInstituciones registradas: " + instituciones.size());
        instituciones.forEach(i -> System.out.printf("%d | %s%n", i.getId(), i.getNombre()));
    }

    /* ---------- R5: Localizaciones ---------- */
    private static void mostrarLocalizaciones() {
        System.out.println("\n--- Localización de casos ---");
        personas.forEach(p ->
                System.out.printf("ID:%d | %s -> %s%n", p.id, p.nombre, p.ubicacion)
        );
    }

    /* ---------- Auxiliares ---------- */
    private static PersonaSituacionCalle buscarPersona(int id) {
        return personas.stream()
                .filter(p -> p.id == id)
                .findFirst().orElse(null);
    }

    private static Institucion buscarInstitucion(int id) {
        return instituciones.stream()
                .filter(i -> i.getId() == id)
                .findFirst().orElse(null);
    }
}