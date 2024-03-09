package org.example.app;

import org.example.app.repository.impl.EmployeeRepository;
import org.example.app.config.NettyServerProvider;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER =
            Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws MalformedURLException {

        final String httpServer = NettyServerProvider.startHttpServer(
                EmployeeRepository.class
        );

        LOGGER.log(Level.INFO, httpServer);
    }
}
