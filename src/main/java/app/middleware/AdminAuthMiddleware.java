package app.middleware;

import app.utils.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

public class AdminAuthMiddleware implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.header("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedResponse("Missing or invalid token");
        }

        token = token.substring(7); // Remove "Bearer " prefix
        DecodedJWT jwt = JwtUtil.validateToken(token);

        if (jwt == null || !"admin".equals(jwt.getClaim("role").asString())) {
            throw new UnauthorizedResponse("Unauthorized access");
        }

        ctx.attribute("username", jwt.getSubject()); // Store username in context
    }

    public void handleAdmin(Context ctx) throws Exception {
        String token = ctx.header("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedResponse("Missing or invalid token");
        }

        token = token.substring(7); // Remove "Bearer " prefix
        DecodedJWT jwt = JwtUtil.validateToken(token);

        if (jwt == null || !"admin".equals(jwt.getClaim("role").asString())) {
            throw new UnauthorizedResponse("Unauthorized access");
        }

        ctx.attribute("username", jwt.getSubject()); // Store username in context
    }
}
