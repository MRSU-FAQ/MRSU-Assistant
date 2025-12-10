package ru.mrsu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.Arrays;
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
        globalCorsConfiguration.setAllowedOriginPatterns(Arrays.asList(
          "http://localhost:5173",           // Локальная разработка (Vite/React)
          "http://localhost:3000",           // Локальная разработка (Vite/React)
          "http://frontend-chat:80",         // Docker-сеть (имя сервиса фронтенда)
          "http://frontend-admin:80"         // Docker-сеть для админки
        ));
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
