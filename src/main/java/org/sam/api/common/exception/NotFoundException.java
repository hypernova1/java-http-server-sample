package org.sam.api.common.exception;

import org.sam.server.constant.HttpStatus;
import org.sam.server.http.context.HttpException;

public class NotFoundException extends HttpException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
