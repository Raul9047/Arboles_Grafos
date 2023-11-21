package Grafo.Excepciones;

public class AristaYaExisteException extends Exception {
    public AristaYaExisteException() {
        super("Arista Ya Existe.");
    }

    public AristaYaExisteException(String message) {
        super(message);
    }
}
