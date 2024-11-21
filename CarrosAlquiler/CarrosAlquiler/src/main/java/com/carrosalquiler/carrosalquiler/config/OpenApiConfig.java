package com.carrosalquiler.carrosalquiler.config;

// Importar las clases necesarias desde el framework Spring y OpenAPI
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para la documentación de OpenAPI.
 * Esta clase establece la configuración para conectarse a OpenAPI para generar documentación de API.
 */
@Configuration
public class OpenApiConfig {
    // Aquí irán métodos que configuren y proporcionen beans relacionados con OpenAPI.

    /**
     * Bean para configurar el grupo de API pública para OpenAPI.
     * Esto hará coincidir todas las rutas y las incluirá en la documentación de OpenAPI bajo el grupo llamado "public".
     *
     * @return una instancia de GroupedOpenApi configurada con el grupo público y todas las rutas.
     */
    @Bean //el objeto devuelto por este método estará disponible como un componente reutilizable en la aplicación.
    //GroupedOpenApi : facilita la integración de OpenAPI/Swagger en aplicaciones Spring Boot. Su función principal es organizar y agrupar las rutas
    public GroupedOpenApi publicApi() { //metodo que declara la retornacion de un objeto tipo grou... (hace parte de biblioteca)
        return GroupedOpenApi.builder()//Inicia la construcción de la configuración de OpenAPI
                .group("public") // Define el nombre del grupo como "public"
                .pathsToMatch("/**")  // Incluye todas las rutas de la aplicación en este grupo
                .build();  // Finaliza y devuelve el objeto configurado
    }
}
