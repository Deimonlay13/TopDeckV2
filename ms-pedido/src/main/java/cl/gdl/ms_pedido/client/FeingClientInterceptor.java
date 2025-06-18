package cl.gdl.ms_pedido.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeingClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            String token = jwtAuth.getToken().getTokenValue();
            System.out.println("🟢 Enviando token a ms-user: " + token);
            template.header("Authorization", "Bearer " + token);
        } else {
            System.out.println("🔴 No se encontró token JWT en el contexto.");
        }
    }
    
}
