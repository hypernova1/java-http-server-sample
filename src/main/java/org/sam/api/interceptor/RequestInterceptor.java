package org.sam.api.interceptor;

import org.sam.server.http.Interceptor;
import org.sam.server.http.Request;
import org.sam.server.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by melchor
 * Date: 2020/08/13
 * Time: 6:40 PM
 */
public class RequestInterceptor implements Interceptor {
    Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Override
    public void preHandler(Request request, Response response) {
        logger.info("protocol: " + request.getProtocol());
        Set<String> headerNames = request.getHeaderNames();
        for (String headerName : headerNames) {
            logger.info(headerName + ": " + request.getHeader(headerName));
        }
        logger.info("http method: " + request.getMethod());
        logger.info("request path: " + request.getPath());
        logger.info("request query: " + request.getParameters());
        logger.info("request body: " + request.getAttributes());

    }

    @Override
    public void postHandler(Request request, Response response) {

    }
}
