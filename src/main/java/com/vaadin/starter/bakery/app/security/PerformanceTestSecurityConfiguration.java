// vaadin-com-generator:exclude
/*
    The line above is a marker for the vaadin.com/start page .zip file generator.
    It ensures that this file is excluded from the generated project package.
*/
package com.vaadin.starter.bakery.app.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import com.vaadin.starter.bakery.backend.data.Role;

/**
 * {@code PerformanceTestSecurityConfiguration} é uma configuração de segurança personalizada
 * para o perfil {@code performance-test}. 
 * <p>
 * Esta configuração permite o acesso a todas as views sem necessidade de login, 
 * facilitando testes de desempenho do sistema sem autenticação.
 * </p>
 *
 * <p>
 * Características principais:
 * <ul>
 *   <li>Desabilita a proteção CSRF para permitir uso de HTML simples durante os testes.</li>
 *   <li>Permite todas as requisições HTTP sem restrição de autenticação.</li>
 *   <li>Define um usuário anônimo com todas as permissões de {@link Role} para simular privilégios elevados durante o teste.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Importante:</b> Esta configuração deve ser utilizada apenas durante testes de performance.
 * </p>
 * 
 * @author LETI-110331
 * @see com.vaadin.flow.spring.security.VaadinWebSecurity
 */
@Configuration
@Order(1)
@Profile("performance-test")
public class PerformanceTestSecurityConfiguration extends VaadinWebSecurity {

    /**
     * Configura o {@link HttpSecurity} para desabilitar CSRF, permitir todas as requisições
     * e definir um usuário anônimo com todas as permissões durante os testes de performance.
     *
     * @param http o objeto {@link HttpSecurity} a ser configurado
     * @throws Exception em caso de erro de configuração
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Not using Spring CSRF here to be able to use plain HTML for the login page
            .csrf(csrf -> csrf.disable())

            // Allow all requests by anonymous users
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )

            // Define the anonymous user with custom principal and roles
            .anonymous(anon -> anon
                .principal(new User("admin@vaadin.com", "",
                    List.of(new SimpleGrantedAuthority("admin"))))
                .authorities(Arrays.stream(Role.getAllRoles())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()))
            );
    }

}
