package com.anhkhoa.travellak.Configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration // Đánh dấu cho Spring biết đây là class config
@EnableWebSecurity // Kích hoạt Spring Security cho project
@EnableMethodSecurity
public class SecurityConfig {

    // Lấy signerKey từ file application
    @Value("${jwt.signerKey}")
    private String signerKey;

    //    @Autowired
    //    private CustomJwtDecoder customJwtDecoder;
    // Những request theo method post được public
    private final String[] PUBLIC_POST_ENDPOINTS = {
        "/Users/SignUp", "/Authentication/**", "/Tours/**", "/DayTour/**", "/Countries", "Travellak/Users/SignUp"
    };

    // Những request theo method get được public
    private final String[] PUBLIC_GET_ENDPOINTS = {
        "/Tours/**",
        "/DayTour",
        "/TourImage",
        "/Attractions",
        "/Cities",
        "/Tours",
        "/create_payment",
        "/Admin/GetChart",
        "/DayTour/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request ->
                        // request được gọi đến
                        request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS)
                                .permitAll() // Allow ALL POST
                                .requestMatchers(HttpMethod.GET, "/**")
                                .permitAll()
                                .
                                //                requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                                // nếu request match với phương thức post và nằm trong danh sách
                                // PUBLIC_POST_ENDPOINTS thì chấp nhận tất cả
                                //                        .requestMatchers(HttpMethod.GET,
                                // PUBLIC_GET_ENDPOINTS).permitAll().
                                //                        .requestMatchers(HttpMethod.GET,
                                // "/Travellak/Users").hasAuthority("ROLE_ADMIN").
                                //                        .requestMatchers(HttpMethod.DELETE, "/Tours/**").permitAll().
                                // nếu request match với phương thức get và nằm trong danh sách
                                // PUBLIC_GET_ENDPOINTS thì chấp nhận tất cả
                                anyRequest()
                                .authenticated());
        // các request còn lại phải được xác thực
        // .oauth2ResourceServer() bật chế độ OAuth2 Resource Server
        // khi bật chế độ này thì các request gửi đến đều có Bearer token trong header
        // spring sẽ tự động kiểm tra token đó
        // Decode và xác thực chữ ký.
        // Kiểm tra hạn, claims…
        // .jwt() chỉ định JWT làm Access Token Format thay vì các token khác
        // .decoder(jwtDecoder())
        // Chỉ định cách Spring giải mã và xác thực token.
        // jwtConfigurer.decoder() nhận vào một JwtDecoder
        // Mà đã định nghĩa bean jwtDecoder() ở dưới
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
                jwtConfigurer.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthenticationConverter())));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    //    Cấu hình cách Spring giải mã và xác thực JWT token
    @Bean
    JwtDecoder jwtDecoder() {
        // Tạo secretKey tương tự lúc tạo token
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                // .withSecretKey(secretKeySpec)
                // → Cấu hình Secret key để giải mã và verify token.
                .withSecretKey(secretKeySpec)
                // → Chỉ định thuật toán dùng để kiểm tra chữ ký của JWT là HMAC-SHA512.
                // (nếu token không ký bằng HS512 → verify sẽ fail).
                .macAlgorithm(MacAlgorithm.HS512)
                // → Tạo ra một instance JwtDecoder hoàn chỉnh.
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
