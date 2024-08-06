package org.sam.api.common.exception;

import org.sam.server.constant.HttpStatus;
import org.sam.server.http.context.HttpException;

public class UnauthorizedException extends HttpException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

}
