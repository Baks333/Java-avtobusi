package com.config;

import com.dao.*;
import com.service.SecurityService;
import com.service.UserDetailsService;
import com.service.UserService;
import com.validator.UserValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.controller")
public class SpringMvcConfig implements WebMvcConfigurer {


    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:validation");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bastour?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public FeedbackDAO getFeedbackDAO() {
        return new FeedbackDAO(getDataSource());
    }

    @Bean
    public ConcertDAO getCartDAO() {
        return new ConcertDAO(getDataSource());
    }

    @Bean
    public RoomDAO getRoomDAO() {
        return new RoomDAO(getDataSource());
    }

    @Bean
    public HotelDAO getCombinationDAO() {
        return new HotelDAO(getDataSource());
    }

    @Bean
    public OrderDAO getOrderDAO() {
        return new OrderDAO(getDataSource());
    }

    @Bean
    public TourDAO getTourDAO() {
        return new TourDAO(getDataSource());
    }

    @Bean
    public UsersDAO getUsersDAO() {
        return new UsersDAO(getDataSource());
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
    @Bean
    public SecurityService getSecurityService() {
        return new SecurityService();
    }
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService();
    }
    @Bean
    public UserValidator getUserValidator() {
        return new UserValidator();
    }
    @Bean
    public AuthenticationManager getAuthenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }
}
