package in.arpit.spring.angularboot;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableResourceServer
public class AngularBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AngularBootApplication.class, args);
    }

    @Bean
    protected ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                        .antMatchers("/", "/*.css", "/*.js").permitAll()
                        .anyRequest().authenticated();
            }
        };
    }

    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                LegacyCookieProcessor legacyCookieProcessor = new LegacyCookieProcessor();
                legacyCookieProcessor.setAllowHttpSepsInV0(true);
                ((TomcatEmbeddedServletContainerFactory) container)
                        .addContextCustomizers((TomcatContextCustomizer)
                                context -> context.setCookieProcessor(legacyCookieProcessor));
            }
        };
    }
}
