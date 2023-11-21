package Grafo.Excepciones;

public class NroVerticesInvalidoException extends Exception{
    public NroVerticesInvalidoException() {
        super("Número de Vértices Invalido :v");
    }

    public NroVerticesInvalidoException(String message) {
        super(message);
    }
}
