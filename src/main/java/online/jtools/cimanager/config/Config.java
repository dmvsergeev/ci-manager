package online.jtools.cimanager.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ComponentScan("online.jtools.cimanager")
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @NotNull
    private final ApplicationContext applicationContext;

    @Autowired
    public Config(@NotNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @NotNull
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    @NotNull
    public ISpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(@NotNull ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**", "/css/**")
                .addResourceLocations("/WEB-INF/resources/", "/WEB-INF/css/");

        registry.addResourceHandler("/resources/**", "/js/**")
                .addResourceLocations("/resources/", "/WEB-INF/js/");

        registry.addResourceHandler("/resources/**", "/src/**")
                .addResourceLocations("/resources/", "/WEB-INF/views/src/");

        registry.addResourceHandler("/resources/**", "/distr/**")
                .addResourceLocations("/resources/", "/WEB-INF/views/distr/");
    }

    public void addViewControllers(@NotNull ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}
