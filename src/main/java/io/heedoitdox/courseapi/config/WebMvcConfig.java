package io.heedoitdox.courseapi.config;

import java.nio.charset.StandardCharsets;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring()
        .requestMatchers("/h2-console/**");
  }

  @Bean
  public StringHttpMessageConverter stringHttpMessageConverter() {
    return new StringHttpMessageConverter(StandardCharsets.UTF_8);
  }
}
