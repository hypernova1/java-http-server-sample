package org.sam.api.handler;

import org.sam.api.domain.Post;
import org.sam.api.payload.JoinReqeust;
import org.sam.api.payload.LoginRequest;
import org.sam.api.service.AuthService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.JsonRequest;
import org.sam.server.annotation.handle.PostHandle;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.ResponseEntity;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 11:47 PM
 */
@Handler("/auth")
public class AuthHandler {

    private final AuthService authService;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
    }

    @PostHandle("/login")
    public ResponseEntity<?> login(@JsonRequest LoginRequest request) {
        boolean result = authService.login(request);
        if (result) return ResponseEntity.ok(null);
        return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
    }

    @PostHandle("/join")
    public ResponseEntity<?> join(@JsonRequest JoinReqeust request) {
        authService.join(request);
        return ResponseEntity.ok(null);
    }

}
