package au.com.developer.api.common.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration class is needed for Swagger UI to work after setting the application.yml properties
 *   spring.mvc.throw-exception-if-no-handler-found = true
 *   spring.resources.add-mappings = false
 * These 2 property changes allow us to throw custom exceptions for HTTP 404 by bypassing some of Spring Boot's
 *   auto-configuration unfortunately it disables automatic static resource mapping (used by Swagger UI) so we
 *   need to add this resource mapping manually, hence the existence of this configuration class.
 * https://github.com/spring-projects/spring-boot/issues/7653
 */
@Configuration
public class SwaggerResourcesConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
