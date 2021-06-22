package au.com.developer.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    private static String segmentName() {
        String applicationName = System.getProperty("spring.application.name");
        String environment = System.getProperty("ap.env");
        return applicationName + "-" + environment;
    }
}
