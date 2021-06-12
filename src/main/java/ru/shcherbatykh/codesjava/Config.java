package ru.shcherbatykh.codesjava;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


@Configuration
@ComponentScan(basePackages = "ru.shcherbatykh.codesjava, ru.shcherbatykh.controllers, ru.shcherbatykh.dao, ru.shcherbatykh.models")
@EnableWebMvc
@EnableJdbcRepositories(basePackages = "ru.shcherbatykh.dao")
public class Config  extends AbstractJdbcConfiguration implements WebMvcConfigurer{
    private final ApplicationContext applicationContext;

    @Autowired
    public Config(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
    
    @Bean
    public DataSource dataSource(){
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setUser("sa");
        h2DataSource.setPassword("");
        h2DataSource.setUrl("jdbc:h2:~/test");
        return h2DataSource;
    }
    
    @Bean @Autowired
    NamedParameterJdbcOperations namedParameter (DataSource ds){
        return new NamedParameterJdbcTemplate(ds);
    }
    @Bean @Autowired
    PlatformTransactionManager transactionManager (DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
