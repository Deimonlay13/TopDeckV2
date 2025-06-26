package cl.gdl.ms_pedido.integration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PedidoIdStorage {

    private static final String FILE_NAME = "pedidoId.txt";

    // Guarda el UUID en un archivo (en el directorio actual)
    public static void saveId(UUID id) {
        Path path = Paths.get(FILE_NAME);
        try {
            Files.writeString(path, id.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el ID en archivo", e);
        }
    }

    // Lee el UUID guardado en el archivo
    public static UUID readId() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            throw new RuntimeException("Archivo con ID no encontrado: " + FILE_NAME);
        }
        try {
            String content = Files.readString(path).trim();
            return UUID.fromString(content);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el ID desde archivo", e);
        }
    }
}