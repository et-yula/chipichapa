package Utility;


import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Optional;

public class HTTPHeaderExtractor {
    private final static String TOKEN_PREFIX = "Bearer";
    public static Optional<String> extractJWT(MultivaluedMap<String, String> headers) {
        try {
            String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            return Optional.of(authorizationHeader).filter(header -> { return header.startsWith(TOKEN_PREFIX); }).map(header -> { return header.substring(TOKEN_PREFIX.length()).trim(); });
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
