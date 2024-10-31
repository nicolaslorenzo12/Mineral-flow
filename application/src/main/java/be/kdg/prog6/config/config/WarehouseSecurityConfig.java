package be.kdg.prog6.config.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// This bean allows the application to define security configurations
@EnableWebSecurity
// This bean allows the application to enforce security constraints directly on individual methods. For example @PreAuthorize("hasRole('ADMIN')")
@EnableMethodSecurity(prePostEnabled = true)
public class WarehouseSecurityConfig {

    @Bean
    // This method is defining a security filter chain. The HttpSecurity parameter allows me to configure web=based security for specific http
    //requests
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // authorizeHttpRequests is used to configure which requests should be authorized based on certain conditions
                .authorizeHttpRequests((authorize) -> authorize
                        // This specifies the root url which for me is "/"
                        .requestMatchers("/").permitAll() // permit all() means that anyone can access the root URL without authentication
                        .anyRequest().authenticated() // anyRequest().authenticated() indicates that every other request must be authenticated
                  //sessionManagement allows me to configure session management in my application
                ).sessionManagement(mgmt -> mgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// The session creation policy is stateless, the server will not store session information
                // oauth2ResourceServer configures the app as an oauth2.0 resource server. It means that it will protect resources and validate
                // incoming access tokens
                .oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults())) //(rs -> rs.jwt(Customizer.withDefaults())) configures my "resource server"  to use JWT tokens. Customizer.withDefaults() applies the default JWT configuration
                // csrf configures Cross-Site Request Forgery protection but I disable it because i am using a stateless app. 
                .csrf(AbstractHttpConfigurer::disable);
        // cors method configures Cross-Origin Resource Sharing which tells the app what origins are permitted to access its resources
        http.cors(Customizer.withDefaults());
        // Finalizes the configuration and builds the security filter chain
        return http.build();
    }

}


