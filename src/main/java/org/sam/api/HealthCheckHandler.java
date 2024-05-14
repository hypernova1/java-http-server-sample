package org.sam.api;

import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.GetMapping;
import org.sam.server.http.web.response.ResponseEntity;

@Handler
public class HealthCheckHandler {

    @GetMapping("/health")
    public ResponseEntity<?> checkHealth() {
        return ResponseEntity.ok(null);
    }

}
