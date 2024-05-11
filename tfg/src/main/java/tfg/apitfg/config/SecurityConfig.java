package tfg.apitfg.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
public class SecurityConfig implements Filter, WebMvcConfigurer {

    @Value("${spring.security.google.public_key}")
    private String googlePublicKey;

    @Value("${spring.security.google.e}")
    private String e;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;
        String jwtToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasLength(jwtToken)) {
            byte[] nBytes = Base64.getUrlDecoder().decode(googlePublicKey);
            byte[] eBytes = Base64.getUrlDecoder().decode(e);

            try {
                RSAPublicKeySpec spec =
                        new RSAPublicKeySpec(new java.math.BigInteger(1, nBytes), new java.math.BigInteger(1, eBytes));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(spec);
                Algorithm algorithm = Algorithm.RSA256(publicKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(jwtToken.replace("Bearer ", ""));
                request.setAttribute("email", decodedJWT.getClaim("email").asString());
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                log.error("Error produced while parsing token {}", httpRequest.getRequestURI(), e);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
    }
}
