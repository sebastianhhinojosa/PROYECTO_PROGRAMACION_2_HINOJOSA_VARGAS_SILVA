import java.util.*;

public class UbicacionEcuador {

    private static final Map<String, List<String>> ubicaciones = new HashMap<>();

    static {
        ubicaciones.put("Quito", List.of("Centro Histórico", "La Mariscal", "Carcelén", "El Condado", "La Carolina"));
        ubicaciones.put("Guayaquil", List.of("Centro", "Urdesa", "Sauces", "Samanes", "Alborada", "9 de Octubre"));
        ubicaciones.put("Cuenca", List.of("El Centro", "Totoracocha", "Miraflores", "El Vecino"));
        ubicaciones.put("Manta", List.of("Tarqui", "Los Esteros", "La Pradera"));
        ubicaciones.put("Ambato", List.of("Huachi", "Atocha", "Ficoa"));
    }

    public static List<String> getCiudades() { return new ArrayList<>(ubicaciones.keySet()); }

    public static List<String> getBarrios(String ciudad) {
        return ubicaciones.getOrDefault(ciudad, Collections.emptyList());
    }

    public static boolean ciudadValida(String ciudad) { return ubicaciones.containsKey(ciudad); }

    public static boolean barrioValido(String ciudad, String barrio) {
        return ciudadValida(ciudad) && ubicaciones.get(ciudad).contains(barrio);
    }
}
