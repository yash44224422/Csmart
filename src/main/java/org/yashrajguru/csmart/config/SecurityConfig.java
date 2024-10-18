package org.yashrajguru.csmart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http//build pattern
                .csrf(customizer -> customizer.disable())//disable csrf
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register","login")//no need auth in register login
                        .permitAll()//up will be  permitted
                        .anyRequest().authenticated())// only authories sees after disable csrf

                // .formLogin(Customizer.withDefaults())//get login form ans  in postman
                .httpBasic(Customizer.withDefaults())//rest api access
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//every time new session id
                //longin form again agani can comment form login
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();//HttpSecurity gives build and build gives object of SecurityFilterChain
    }
//// more detail
//
//      http. csrf(customizer -> customizer.disable());//disable csrf
//      http.authorizeHttpRequests(request -> request.anyRequest().authenticated());// only authories sees after disable csrf
//     // http.formLogin(Customizer.withDefaults());//get login form ans  in postman
//      http.httpBasic(Customizer.withDefaults());//rest api access
//      http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//every time new session id
//      //longin form again agani can comment form login
    //  return http.build();//HttpSecurity gives build and build gives object of SecurityFilterChain


//////behid labda
//      Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//          @Override
//          public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//              customizer.disable();
//
//          }
//      };
//
//              http.csrf(custCsrf);

    // default authaticadte in code
//      @Bean
//      public UserDetailsService userDetailsService(){
//
//          UserDetails user1= User
//                  .withDefaultPasswordEncoder()
//                  .username("Rajguru")
//                  .password("@123")
//                  .roles("USER")
//                  .build();
//
//          UserDetails user2= User
//                  .withDefaultPasswordEncoder()
//                  .username("YashRajguru")
//                  .password("Y@123")
//                  .roles("ADMIN")
//                  .build();
//
//          return new InMemoryUserDetailsManager(user1 ,user2); //user detail service  is need but this is interface make methode
//      }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder (new BCryptPasswordEncoder(12));//(NoOpPasswordEncoder.getInstance());// not for verify bcrpt
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
