package cl.gdl.ms_pedido.errors;

public class NameNumberException extends BaseServiceException{
    public NameNumberException(String nombre) {
        super("El campo '" + nombre + "' no puede contener números.");
    }
}
