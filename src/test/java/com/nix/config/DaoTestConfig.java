package com.nix.config;

import com.nix.dao.RoleDao;
import com.nix.dao.UserDao;
import com.nix.dao.hiber.HRoleDao;
import com.nix.dao.hiber.HUserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:app-test.properties"})
@EnableTransactionManagement
public class DaoTestConfig {

    @Autowired
    private Environment env;

    @Bean
    public RoleDao roleDao() {
        return new HRoleDao(sessionFactory());
    }

    @Bean
    public UserDao userDao() {
        return new HUserDao(sessionFactory());
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("dbunit-data/schema.sql")
                .ignoreFailedDrops(true)
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.nix.model")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager txManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory());
        return txManager;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.cache.use_second_level_cache",
                        env.getProperty("hibernate.cache.use_second_level_cache"));
                setProperty("hibernate.cache.use_query_cache",
                        env.getProperty("hibernate.cache.use_query_cache"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.max_fetch_depth",
                        env.getProperty("hibernate.max_fetch_depth"));
                setProperty("show_sql", env.getProperty("hibernate.show_sql"));
                setProperty("format_sql", env.getProperty("hibernate.format_sql"));
                setProperty("hibernate.fetch_size", env.getProperty("hibernate.fetch_size"));
                setProperty("hibernate.batch_size", env.getProperty("hibernate.batch_size"));
                setProperty("javax.persistence.validation.mode", "none");

            }
        };
    }

//    @Bean
//    public UserService userService() {
//        return Mockito.mock(UserServiceImpl.class);
//    }
//
//    @Bean
//    public RoleService roleService() {
//        return Mockito.mock(RoleService.class);
//    }
//
//    @Bean
//    public ReCaptchaImpl reCaptcha() {
//        return Mockito.mock(ReCaptchaImpl.class);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//        GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
//
//        UserDetails adminUserDetails = new User("adminLogin", "adminPass",
//                asList(adminAuthority));
//        UserDetails userUserDetails = new User("userLogin", "userPass",
//                asList(userAuthority));
//
//        return new InMemoryUserDetailsManager(asList(adminUserDetails, userUserDetails));
//    }

//    @Bean
//    @Primary
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        return authenticationProvider;
//    }

}
