package org.sam.api;

import org.sam.server.annotation.ComponentScan;
import org.sam.server.http.HttpServer;

/**
 * Created by melchor
 * Date: 2020/07/18
 * Time: 2:16 AM
 */
@ComponentScan
public class Application {
    public static void main(String[] args) {
        HttpServer.start();
    }
}
