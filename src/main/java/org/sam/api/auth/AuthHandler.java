package org.sam.api.auth;

import org.sam.api.exception.BadRequestException;
import org.sam.api.member.Member;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.GetMapping;
import org.sam.server.annotation.handle.JsonRequest;
import org.sam.server.annotation.handle.PathValue;
import org.sam.server.annotation.handle.PostMapping;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.Session;
import org.sam.server.http.web.response.ResponseEntity;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@JsonRequest LoginRequest request, Session session) {
        Member member = authService.login(request);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(member.getId());
        loginUser.setEmail(member.getEmail());
        loginUser.setName(member.getName());
        session.setAttribute("loginUser", loginUser);
        return ResponseEntity.ok(loginUser);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@JsonRequest JoinRequest request) {
        authService.join(request);
        return ResponseEntity.of(HttpStatus.CREATED);
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<?> checkEmail(@PathValue String email) {
        boolean existsEmail = authService.existsEmail(email);
        if (existsEmail) {
            throw new BadRequestException();
        }
        return ResponseEntity.ok();
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(Session session) {
        session.invalidate();
        return ResponseEntity.ok();
    }

}
