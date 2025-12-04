package ru.mrsu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.List;

@Configuration
public class SecurityConfig{
    @Bean
    CorsFilter corsFilter() {
        // Источник конфигураций CORS
        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // Конфигурация CORS
        var globalCorsConfiguration = new CorsConfiguration();

        // Разрешаются CORS-запросы:
        // - с любого сайта. TODO: Заменить на конкретный адрес
        globalCorsConfiguration.addAllowedOrigin("*");
        // - с методами GET, POST, PUT, PATCH и DELETE
        globalCorsConfiguration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));

        globalCorsConfiguration.addAllowedHeader("*");

        // JavaScript может обращаться к заголовку X-OTHER-CUSTOM-HEADER ответа
        globalCorsConfiguration.setExposedHeaders(List.of("X-OTHER-CUSTOM-HEADER"));
        // Браузер может кешировать настройки CORS на 10 секунд
        globalCorsConfiguration.setMaxAge(Duration.ofSeconds(10));

        // Использование конфигурации CORS для всех запросов
        corsConfigurationSource.registerCorsConfiguration("/**", globalCorsConfiguration);

        return new CorsFilter(corsConfigurationSource);
    }
}
