package br.com.tspaschoal.forumalura.config.filters;

import antlr.Token;
import br.com.tspaschoal.forumalura.models.entities.Usuario;
import br.com.tspaschoal.forumalura.repositories.UserRepository;
import br.com.tspaschoal.forumalura.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository userRepository;

    public AuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromHeader(httpServletRequest);

        Boolean isValid = this.tokenService.isValidToken(token);

        if (isValid) {
            authenticate(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void authenticate(String token) {
        Long userId = tokenService.getUserIdFromToken(token);
        Usuario usuario = this.userRepository.findById(userId).get();
        var user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getRoles());
        SecurityContextHolder.getContext().setAuthentication(user);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
