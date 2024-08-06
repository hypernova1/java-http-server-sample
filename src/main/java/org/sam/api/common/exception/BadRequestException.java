package org.sam.api.common.exception;

import org.sam.server.constant.HttpStatus;
import org.sam.server.http.context.HttpException;

public class BadRequestException extends HttpException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

}
