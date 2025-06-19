package cl.gdl.ms_pedido.errors;

public class NameNullException extends BaseServiceException{
    public NameNullException(String fieldName) {
        super("El campo '" + fieldName + "' no debe ser nulo.");
    }
}
