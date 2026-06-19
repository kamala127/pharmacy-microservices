package com.pharma.medicine_service.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) {

        try {

            response.setStatus(
                    HttpServletResponse.SC_FORBIDDEN);

            response.setContentType(
                    "application/json");

            Map<String, Object> error =
                    new HashMap<>();

            error.put(
                    "timestamp",
                    LocalDateTime.now());

            error.put(
                    "status",
                    403);

            error.put(
                    "error",
                    "Access Denied");

            error.put(
                    "message",
                    "You are not authorized to perform this action");

            error.put(
                    "path",
                    request.getRequestURI());

            new ObjectMapper()
                    .writeValue(
                            response.getOutputStream(),
                            error);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
