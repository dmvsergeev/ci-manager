package online.jtools.cimanager.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @NotNull
    private final DataSource dataSource;

    public WebSecurityConfig(@NotNull DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/passwords", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username, password, active from public.\"Users\" where username=?")
                .authoritiesByUsernameQuery("select \n" +
                        "u.username, ur.roles \n" +
                        "from public.\"Users\" u \n" +
                        "inner join public.\"Roles\" ur on u.id = ur.user_id \n" +
                        "where u.username=?");
    }
}