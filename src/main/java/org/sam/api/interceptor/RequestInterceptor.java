package org.sam.api.interceptor;

import org.sam.server.http.Interceptor;
import org.sam.server.http.web.Request;
import org.sam.server.http.web.Response;

import java.util.Map;

public class RequestInterceptor implements Interceptor {
    @Override
    public void preHandler(Request request, Response response) {
        System.out.println("==================================================================");

        System.out.println("Request Path: " + request.getUrl());
        System.out.println("Request Method: " + request.getMethod());

        Map<String, String> parameters = request.getParameters();
        if (request.getParameters().keySet().size() > 0) {
            System.out.println("-------------------------- Parameter --------------------------");
            for (Map.Entry<String, String> e : parameters.entrySet()) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }
        }

        if (request.getJson() != null) {
            System.out.println("-------------------------- Request Body --------------------------");
            System.out.println(request.getJson());
        }

        System.out.println("==================================================================");
    }

    @Override
    public void postHandler(Request request, Response response) {}
}
