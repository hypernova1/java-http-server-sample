package org.sam.api.handler;

import org.sam.api.domain.LoginUser;
import org.sam.api.domain.Member;
import org.sam.api.payload.JoinRequest;
import org.sam.api.payload.LoginRequest;
import org.sam.api.service.AuthService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.GetHandle;
import org.sam.server.annotation.handle.JsonRequest;
import org.sam.server.annotation.handle.PathValue;
import org.sam.server.annotation.handle.PostHandle;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.Session;
import org.sam.server.http.web.ResponseEntity;

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
    public ResponseEntity<?> login(@JsonRequest LoginRequest request, Session session) {
        Member member = authService.login(request);
        if (member == null) return ResponseEntity.notFound(null);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(member.getId());
        loginUser.setEmail(member.getEmail());
        loginUser.setName(member.getName());
        session.setAttribute("loginUser", loginUser);
        return ResponseEntity.ok(loginUser);
    }

    @PostHandle("/join")
    public ResponseEntity<?> join(@JsonRequest JoinRequest request) {
        boolean result = authService.join(request);
        if (!result) return ResponseEntity.badRequest(null);
        return ResponseEntity.of(HttpStatus.CREATED, null);
    }

    @GetHandle("/check-email/{email}")
    public ResponseEntity<?> checkEmail(@PathValue String email) {
        boolean result = authService.isAvailableEmail(email);
        if (!result) return ResponseEntity.badRequest(null);
        return ResponseEntity.ok(null);
    }

    @GetHandle("/logout")
    public ResponseEntity<?> logout(Session session) {
        session.invalidate();
        return ResponseEntity.ok(null);
    }

}
