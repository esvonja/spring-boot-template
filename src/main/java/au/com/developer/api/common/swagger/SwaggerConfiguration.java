package au.com.developer.api.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Arrays.asList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("au.com.developer.api.web.controller.rest"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(asList(basicAuth()))
                .securityContexts(asList(securityContext()));
    }

    private BasicAuth basicAuth() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext securityContext() {
        AuthorizationScope[] scopes = {
                // new AuthorizationScope("global", "Access everything")
        };
        return SecurityContext.builder()
                .securityReferences(asList(new SecurityReference("basicAuth", scopes)))
                .forPaths(PathSelectors.regex("/api/v1.*"))
                .build();
    }
}
