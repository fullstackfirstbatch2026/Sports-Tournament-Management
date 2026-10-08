package com.example.sportstournament.exception;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Turns errors (including SIGNAL messages from the stored procedure) into {"error": "..."} JSON. */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> badInput(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", String.valueOf(e.getMessage())));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> database(DataAccessException e) {
        Throwable root = NestedExceptionUtils.getMostSpecificCause(e);
        if (root instanceof SQLException sql && "45000".equals(sql.getSQLState())) {
            return ResponseEntity.badRequest().body(Map.of("error", String.valueOf(root.getMessage())));
        }
        if (e instanceof DataIntegrityViolationException) {
            String msg = String.valueOf(root.getMessage()).contains("Duplicate")
                    ? "Duplicate entry (for example, a score already exists for this team and match)"
                    : "Invalid data or reference (check the team and match ids)";
            return ResponseEntity.badRequest().body(Map.of("error", msg));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", String.valueOf(root.getMessage())));
    }
}
