package cl.gdl.ms_pedido.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
