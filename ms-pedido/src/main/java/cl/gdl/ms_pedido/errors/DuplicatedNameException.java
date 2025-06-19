package cl.gdl.ms_pedido.errors;

public class DuplicatedNameException extends BaseServiceException {
    public DuplicatedNameException(String name) {
           super("Ya existe una categoria con el nombre: " + name);
       }
   }