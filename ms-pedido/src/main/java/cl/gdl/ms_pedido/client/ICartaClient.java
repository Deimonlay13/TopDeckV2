package cl.gdl.ms_pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.gdl.ms_pedido.dto.CartaDTO;

@FeignClient(name = "ms-pokeapi")
public interface ICartaClient {
    @GetMapping("/api/pokemon-cards/{id}")
    CartaDTO  getCartaPorId(@PathVariable("id") String id);
}
