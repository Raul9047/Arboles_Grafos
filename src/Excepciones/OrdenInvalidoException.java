package Excepciones;

public class OrdenInvalidoException extends Exception{

    public OrdenInvalidoException() {
        super("El Orden Minimo es 3");
    }

    public OrdenInvalidoException(String message) {
        super(message);
    }
}
