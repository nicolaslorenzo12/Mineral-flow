package be.kdg.prog6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // The "/**" means that all the endpoints of the resource server should follow these cors rules
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // It allows any header sent in a request
                .allowedHeaders("*")
                // It indicates if the browser should include credentials(tokens authentication). We set it to true, so it has to include it
                .allowCredentials(true);
    }
}
