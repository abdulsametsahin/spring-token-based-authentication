package dev.asametsahin.springsecuritytokenbasedauthentications.filters;

import dev.asametsahin.springsecuritytokenbasedauthentications.entity.User;
import dev.asametsahin.springsecuritytokenbasedauthentications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {
            String apiToken = authorizationHeader.replace(PREFIX, "");
            User user = userRepository.findByApiToken(apiToken);

            if (user != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
