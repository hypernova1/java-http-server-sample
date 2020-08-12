package org.sam.api;

import org.sam.server.HttpServer;
import org.sam.server.annotation.ComponentScan;

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
