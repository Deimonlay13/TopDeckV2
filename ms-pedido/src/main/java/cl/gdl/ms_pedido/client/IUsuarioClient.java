package cl.gdl.ms_pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import cl.gdl.ms_pedido.dto.UsuarioDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ms-user", url = "localhost:8090")
public interface IUsuarioClient {

    @GetMapping("/api/user/search/{idPedido}")
    List<UsuarioDTO> findAllUsuariosByPedidos(@PathVariable UUID idPedido);

}
