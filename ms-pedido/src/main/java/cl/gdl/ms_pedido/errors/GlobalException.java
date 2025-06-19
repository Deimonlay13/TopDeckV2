package cl.gdl.ms_pedido.errors;

import java.time.Instant;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.gdl.ms_pedido.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(BaseServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleBaseServiceException(BaseServiceException ex,
            HttpServletRequest request) {
        // Definir el estado HTTP en función de la excepción
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }   else if (ex instanceof NoDataException) {
                status = HttpStatus.NOT_FOUND;
        }

        ErrorResponseDTO error = new ErrorResponseDTO(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest request) {
        // Manejo de excepciones genéricas
        ErrorResponseDTO error = new ErrorResponseDTO(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error inesperado",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
