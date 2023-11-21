package Grafo.Excepciones;

public class AristaNoExisteException extends Exception{
    public AristaNoExisteException() {
        super("Arista No Existe");
    }

    public AristaNoExisteException(String message) {
        super(message);
    }
}
