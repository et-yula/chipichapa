package REST.AuthFilter;

import Models.Error;
import Utility.HTTPHeaderExtractor;
import Utility.JWTUtility;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import jakarta.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;


@Secured
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Optional<UserPrincipal> optionalUser = HTTPHeaderExtractor.extractJWT((MultivaluedMap<String, String>) requestContext.getHeaders()).flatMap(JWTUtility::verify);

        if (!optionalUser.isPresent()) {
            Error error = new Error("UNAUTHORIZED", null);
            Response response = Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
            requestContext.abortWith(response);
            return;
        }

        UserPrincipal user = optionalUser.get();

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() { return user; }
            @Override
            public boolean isUserInRole(String role) { return true; }
            @Override
            public boolean isSecure() { return true; }
            @Override
            public String getAuthenticationScheme() { return null;}
        });
    }
}
