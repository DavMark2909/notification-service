package com.tasksoft.mark.notificationservice.security;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.List;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private final JwtDecoder jwtDecoder;

    public CustomHandshakeHandler(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String token = authHeaders.get(0).replace("Bearer ", "");

            String username = jwtDecoder.decode(token).getSubject();

            if (username != null) {
                return new StompPrincipal(username);
            }
        }

        return null;
    }
}
