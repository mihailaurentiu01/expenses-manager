package com.adriandevv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Esta clase define la configuración de Seguridad de Spring. Configuración en lo que respecta a
 * restricciones de URL, roles, acceso al repositorio de usuarios, etc.
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath:database.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${user}")
    private String dbUser;
    @Value("${password}")
    private String dbPassword;
    @Value("${url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.dbUrl);
        dataSource.setUsername(this.dbUser);
        dataSource.setPassword(this.dbPassword);
        return dataSource;
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery
                ("SELECT username, password, enabled from users WHERE username = ?").passwordEncoder(new BCryptPasswordEncoder())
                .authoritiesByUsernameQuery
                        ("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON " +
                                "r.user_id=u.id WHERE u.username=?");

        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/**")
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().and().logout();

        super.configure(http);
    }
}
