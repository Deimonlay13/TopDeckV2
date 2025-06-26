package cl.gdl.ms_pedido.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("test") // <-- Esta config se activa solo con perfil test
@Configuration
public class SecurityTestConfig {

    //  desactiva seguridad para el perfil test
    @Bean
    public SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}


