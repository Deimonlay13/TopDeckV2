package cl.gdl.ms_pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import cl.gdl.ms_pedido.dto.UsuarioDTO;


import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-users")
public interface IUsuarioClient {

    @GetMapping("/keycloak/user/{idUsuario}")
    UsuarioDTO getUsuarioById(@PathVariable("idUsuario") String idUsuario);
}

