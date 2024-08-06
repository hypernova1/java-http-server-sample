package org.sam.api.common;

import org.sam.api.common.exception.NotFoundException;
import org.sam.api.post.domain.PostNotFoundException;
import org.sam.server.annotation.ExceptionResponse;
import org.sam.server.annotation.component.Handler;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.context.HttpException;
import org.sam.server.http.web.HttpExceptionHandler;
import org.sam.server.http.web.response.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Handler
public class ExceptionHandler implements HttpExceptionHandler {

    @ExceptionResponse(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionResponse(PostNotFoundException.class)
    public ResponseEntity<?> postNotFoundException(PostNotFoundException e) {
        System.out.println("postNotFoundException");
        return new ResponseEntity<>(e.getStatus());
    }

    @ExceptionResponse(HttpException.class)
    public ResponseEntity<?> httpException(HttpException e) {
        System.out.println("httpException");
        Map<String, String> map = new HashMap<>();
        map.put("cause", e.getMessage());
        return new ResponseEntity<>(e.getStatus(), map);
    }

//    @ExceptionResponse(NullPointerException.class)
//    public ResponseEntity<?> nullPointException(NullPointerException e) {
//        System.out.println("nullPointException");
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
