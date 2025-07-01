import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<PersonaSituacionCalle> personas = new ArrayList<>();
    private static final List<Institucion> instituciones = new ArrayList<>();
    private static final List<Rescatista> rescatistas = new ArrayList<>();

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


    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
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

    private static void mostrarMenu() {
        System.out.println("\n--- SISTEMA DE REGISTRO DE PERSONAS EN SITUACIÓN DE CALLE ---");
        System.out.println("1. Registro de personas (R1)");
        System.out.println("2. Registro de atención (R2)");
        System.out.println("3. Gestión de instituciones (R3)");
        System.out.println("4. Reportes y estadísticas (R4)");
        System.out.println("5. Localización de casos (R5)");
        System.out.println("6. Salir");
    }

    private static void registrarPersona() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        int edad = leerEntero("Edad: ");
        System.out.print("Género (Masculino/Femenino/Otro): ");
        String genero = sc.nextLine();

        String ciudad;
        do {
            System.out.print("Ciudad: ");
            ciudad = sc.nextLine();
            if (!UbicacionEcuador.ciudadValida(ciudad)) {
                System.out.println(" Ciudad no reconocida. Ciudades válidas: " + UbicacionEcuador.getCiudades());
            }
        } while (!UbicacionEcuador.ciudadValida(ciudad));

        String barrio;
        do {
            System.out.print("Barrio: ");
            barrio = sc.nextLine();
            if (!UbicacionEcuador.barrioValido(ciudad, barrio)) {
                System.out.println(" Barrio no válido para " + ciudad + ". Barrios válidos: " +
                        UbicacionEcuador.getBarrios(ciudad));
            }
        } while (!UbicacionEcuador.barrioValido(ciudad, barrio));

        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        try {
            PersonaSituacionCalle p = new PersonaSituacionCalle(
                    personas.size() + 1, nombre, edad, genero,
                    ciudad + " - " + barrio, estadoSalud, new Date()
            );
            personas.add(p);
            System.out.println("Persona registrada con ID: " + p.id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.print("¿Desea registrar al rescatista que encontró a esta persona? (s/n): ");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            registrarRescatista();
        }
    }

    private static void registrarAtencion() {
        if (personas.isEmpty() || instituciones.isEmpty()) {
            System.out.println("Debe haber al menos una persona y una institución registrada.");
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
        System.out.println("Atención registrada.");
    }

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

    private static void generarReporte() {
        System.out.println("\n--- Reporte general ---");
        System.out.println("Personas registradas: " + personas.size());
        for (PersonaSituacionCalle p : personas) {
            System.out.printf("ID:%d | Nombre:%s | Atenciones:%d%n",
                    p.id, p.nombre, p.getHistorial().size());
        }

        System.out.println("\nInstituciones registradas: " + instituciones.size());
        instituciones.forEach(i -> System.out.printf("%d | %s%n", i.getId(), i.getNombre()));

        System.out.println("\nRescatistas registrados: " + rescatistas.size());
        rescatistas.forEach(r -> System.out.printf("ID:%d | %s | IDInst: %s%n",
                r.id, r.nombre, r.getIdInstitucional()));
    }

    private static void mostrarLocalizaciones() {
        System.out.println("\n--- Localización de casos ---");
        personas.forEach(p ->
                System.out.printf("ID:%d | %s -> %s%n", p.id, p.nombre, p.ubicacion)
        );
    }

    private static void registrarRescatista() {
        if (instituciones.isEmpty()) {
            System.out.println("Primero debe registrar al menos una institución.");
            return;
        }

        System.out.print("Nombre del rescatista: ");
        String nombre = sc.nextLine();
        int edad = leerEntero("Edad: ");
        System.out.print("Género: ");
        String genero = sc.nextLine();

        String ciudad;
        do {
            System.out.print("Ciudad: ");
            ciudad = sc.nextLine();
            if (!UbicacionEcuador.ciudadValida(ciudad)) {
                System.out.println(" Ciudad no reconocida. Ciudades válidas: " + UbicacionEcuador.getCiudades());
            }
        } while (!UbicacionEcuador.ciudadValida(ciudad));

        String barrio;
        do {
            System.out.print("Barrio: ");
            barrio = sc.nextLine();
            if (!UbicacionEcuador.barrioValido(ciudad, barrio)) {
                System.out.println("⚠ Barrio no válido para " + ciudad + ". Barrios válidos: " +
                        UbicacionEcuador.getBarrios(ciudad));
            }
        } while (!UbicacionEcuador.barrioValido(ciudad, barrio));

        System.out.print("Estado de salud: ");
        String estadoSalud = sc.nextLine();

        int idInst = leerEntero("ID de la institución a la que pertenece: ");
        Institucion inst = buscarInstitucion(idInst);
        if (inst == null) {
            System.out.println("Institución no encontrada.");
            return;
        }

        System.out.print("ID institucional del rescatista: ");
        String idInstitucional = sc.nextLine();

        Rescatista r = new Rescatista(rescatistas.size() + 1, nombre, edad, genero,
                ciudad + " - " + barrio, estadoSalud, idInstitucional, inst);

        rescatistas.add(r);
        System.out.println(" Rescatista registrado exitosamente.");
    }

    private static PersonaSituacionCalle buscarPersona(int id) {
        return personas.stream().filter(p -> p.id == id).findFirst().orElse(null);
    }

    private static Institucion buscarInstitucion(int id) {
        return instituciones.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
