/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import server.cntl.ServerConnector;

/**
 *
 * @author Eric
 */
public class ServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerConnector connector = new ServerConnector(5025);
        connector.start();
    }
}
