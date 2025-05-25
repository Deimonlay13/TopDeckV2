package cl.gdl.ms_pedido.errors;

public class NoDataException  extends BaseServiceException{
    public NoDataException() {
        super("No existen datos.");
    }
}

