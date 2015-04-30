package server.main;

import server.cntl.ServerConnector;

public class ServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerConnector connector = new ServerConnector(5025);
        connector.start();
    }
}
