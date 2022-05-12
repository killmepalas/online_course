//package kill.me.palas.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.authentication.builders.*;
//import org.springframework.security.config.annotation.web.configuration.*;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("student").password("qwerty").roles("STUDENT").and()
//                .withUser("admin").password("qwerty").roles("ADMIN").and()
//                .withUser("teacher").password("qwerty").roles("TEACHER");
//    }
//}
