package Excepciones;

public class ClaveNoExisteException extends Exception {
    public ClaveNoExisteException() {
        super ("Dato No Existe...");
    }

    public ClaveNoExisteException(String msg) {
        super(msg);
    }
}
