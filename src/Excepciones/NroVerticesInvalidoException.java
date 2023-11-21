package Excepciones;

public class NroVerticesInvalidoException extends Exception {
    public NroVerticesInvalidoException() {
        super("Nro de Vertices Invalido");
    }

    public NroVerticesInvalidoException(String msg) {
        super(msg);
    }
}
