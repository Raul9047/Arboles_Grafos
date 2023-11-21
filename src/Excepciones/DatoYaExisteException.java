package Excepciones;

public class DatoYaExisteException extends Exception{
    public DatoYaExisteException() { super("Dato Ya Existe, No se puede ingresar");}

    public DatoYaExisteException(String msg) { super(msg);}
}
