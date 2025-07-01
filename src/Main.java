import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<PersonaSituacionCalle> personas  = new ArrayList<>();
    private static final List<Institucion>           instituciones = new ArrayList<>();
    private static final List<Rescatista>            rescatistas   = new ArrayList<>();

    /* === utilidades === */
    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println(" Error: debe ingresar un número entero.");
            }
        }
    }

    /* === punto de entrada === */
    public static void main(String[] args) {

        configurarInstitucionesIniciales();
        configurarRescatistasIniciales();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarPersona();
                case 2 -> registrarAtencion();
                case 3 -> registrarInstitucion();          // permitir más instituciones luego
                case 4 -> registrarRescatista();           // permitir más rescatistas luego
                case 5 -> generarReporte();
                case 6 -> mostrarLocalizaciones();
                case 7 -> System.out.println("Gracias por usar el sistema.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);

        sc.close();
    }

    /* === configuración inicial === */
    private static void configurarInstitucionesIniciales() {
        System.out.println("--- CONFIGURACIÓN DE INSTITUCIONES ---");
        do {
            registrarInstitucion();
            System.out.print("¿Agregar otra institución? (s/n): ");
        } while (sc.nextLine().equalsIgnoreCase("s"));

        while (instituciones.isEmpty()) {
            System.out.println("Debe registrar al menos una institución.");
            registrarInstitucion();
        }
    }

    private static void configurarRescatistasIniciales() {
        if (instituciones.isEmpty()) return;

        System.out.println("--- CONFIGURACIÓN DE RESCATISTAS ---");
        do {
            registrarRescatista();
            System.out.print("¿Agregar otro rescatista? (s/n): ");
        } while (sc.nextLine().equalsIgnoreCase("s"));
    }

    /* === menú principal === */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL DEL SISTEMA ---");
        System.out.println("1. Registrar persona en situación de calle");
        System.out.println("2. Registrar atención a persona");
        System.out.println("3. Registrar nueva institución");
        System.out.println("4. Registrar nuevo rescatista");
        System.out.println("5. Reportes y estadísticas");
        System.out.println("6. Localización de casos");
        System.out.println("7. Salir");
    }

    /* === R1: personas === */
    private static void registrarPersona() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        int edad = leerEntero("Edad: ");
        System.out.print("Género (Masculino/Femenino/Otro): ");
        String genero = sc.nextLine();

        // validar ciudad y barrio
        String ciudad;
        do {
            System.out.print("Ciudad: ");
            ciudad = sc.nextLine();
            if (!UbicacionEcuador.ciudadValida(ciudad))
                System.out.println("⚠ Ciudad no reconocida. Válidas: " + UbicacionEcuador.getCiudades());
        } while (!UbicacionEcuador.ciudadValida(ciudad));

        String barrio;
        do {
            System.out.print("Barrio: ");
            barrio = sc.nextLine();
            if (!UbicacionEcuador.barrioValido(ciudad, barrio))
                System.out.println("⚠ Barrio no válido. Válidos: " + UbicacionEcuador.getBarrios(ciudad));
        } while (!UbicacionEcuador.barrioValido(ciudad, barrio));

        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        PersonaSituacionCalle p = new PersonaSituacionCalle(
                personas.size() + 1, nombre, edad, genero,
                ciudad + " - " + barrio, estadoSalud, new Date()
        );
        personas.add(p);
        System.out.println("✔ Persona registrada con ID: " + p.id);

        // ¿registrar rescatista asociado?
        System.out.print("¿Registrar al rescatista que la encontró? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) registrarRescatista();
    }

    /* === R2: atenciones === */
    private static void registrarAtencion() {
        if (personas.isEmpty() || instituciones.isEmpty()) {
            System.out.println("Debe haber al menos una persona y una institución.");
            return;
        }

        int idPersona = leerEntero("ID de persona: ");
        PersonaSituacionCalle persona = buscarPersona(idPersona);
        if (persona == null) { System.out.println("Persona no encontrada."); return; }

        System.out.print("Tipo de atención brindada: ");
        String tipo = sc.nextLine();

        int idInst = leerEntero("ID de institución: ");
        Institucion inst = buscarInstitucion(idInst);
        if (inst == null) { System.out.println("Institución no encontrada."); return; }

        System.out.print("Encargado de seguimiento: ");
        String encargado = sc.nextLine();

        Atencion at = new Atencion(persona.getHistorial().size() + 1,
                tipo, new Date(), inst, encargado);
        persona.agregarAtencion(at);
        System.out.println("✔ Atención registrada.");
    }

    /* === R3: instituciones === */
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

    /* === R4: rescatistas === */
    private static void registrarRescatista() {
        if (instituciones.isEmpty()) {
            System.out.println("Primero debe haber al menos una institución.");
            return;
        }

        System.out.print("Nombre del rescatista: ");
        String nombre = sc.nextLine();
        int edad = leerEntero("Edad: ");
        System.out.print("Género: ");
        String genero = sc.nextLine();

        // validar ciudad y barrio
        String ciudad;
        do {
            System.out.print("Ciudad: ");
            ciudad = sc.nextLine();
            if (!UbicacionEcuador.ciudadValida(ciudad))
                System.out.println("⚠ Ciudad no reconocida. Válidas: " + UbicacionEcuador.getCiudades());
        } while (!UbicacionEcuador.ciudadValida(ciudad));

        String barrio;
        do {
            System.out.print("Barrio: ");
            barrio = sc.nextLine();
            if (!UbicacionEcuador.barrioValido(ciudad, barrio))
                System.out.println("⚠ Barrio no válido. Válidos: " + UbicacionEcuador.getBarrios(ciudad));
        } while (!UbicacionEcuador.barrioValido(ciudad, barrio));

        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        int idInst = leerEntero("ID de la institución a la que pertenece: ");
        Institucion inst = buscarInstitucion(idInst);
        if (inst == null) { System.out.println("Institución no encontrada."); return; }

        System.out.print("ID institucional del rescatista: ");
        String idInstitucional = sc.nextLine();

        Rescatista r = new Rescatista(rescatistas.size() + 1, nombre, edad, genero,
                ciudad + " - " + barrio, estadoSalud,
                idInstitucional, inst);
        rescatistas.add(r);
        System.out.println("✔ Rescatista registrado.");
    }

    private static void generarReporte() {
        System.out.println("\n--- Reporte general ---");
        System.out.println("Personas registradas: " + personas.size());
        personas.forEach(p ->
                System.out.printf("ID:%d | Nombre:%s | Atenciones:%d%n", p.id, p.nombre, p.getHistorial().size())
        );

        System.out.println("\nInstituciones registradas: " + instituciones.size());
        instituciones.forEach(i ->
                System.out.printf("ID:%d | %s | Tipo:%s | Contacto:%s%n", i.getId(), i.getNombre(), i.getTipo(), i.getContacto())
        );

        System.out.println("\nRescatistas registrados: " + rescatistas.size());
        rescatistas.forEach(r ->
                System.out.printf("ID:%d | %s | Inst:%s | IDInst:%s%n", r.id, r.nombre, r.getInstitucion().getNombre(), r.getIdInstitucional())
        );
    }


    /* === R6: localizaciones === */
    private static void mostrarLocalizaciones() {
        System.out.println("\n--- Localización de casos ---");
        personas.forEach(p -> System.out.printf("ID:%d | %s -> %s%n", p.id, p.nombre, p.ubicacion));

    }

    /* === auxiliares === */
    private static PersonaSituacionCalle buscarPersona(int id) {
        return personas.stream().filter(p -> p.id == id).findFirst().orElse(null);
    }
    private static Institucion buscarInstitucion(int id) {
        return instituciones.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
