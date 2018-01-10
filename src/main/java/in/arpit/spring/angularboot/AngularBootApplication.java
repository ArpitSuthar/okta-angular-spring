package in.arpit.spring.angularboot;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AngularBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AngularBootApplication.class, args);
    }


    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/**").authorizeRequests()
                    .antMatchers("/", "/*.css", "/*.js").permitAll()
                    .anyRequest().authenticated();
        }
    }

    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory) container)
                        .addContextCustomizers((TomcatContextCustomizer)
                                context -> context.setCookieProcessor(new LegacyCookieProcessor()));
            }
        };
    }
}
