/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.webserver;

import com.rmit.sea.dungeon.resources.Constant;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Steve
 */
public class WebServer extends Thread implements Runnable {

    @Override
    public void run() {
        ServerSocket s;

        System.out.println("Webserver starting up on port "+Constant.WEB_SERVER_PORT);
        
        try {
            // create the main server socket
            s = new ServerSocket(Constant.WEB_SERVER_PORT);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }

        System.out.println("Waiting for connection");
        for (;;) {
            try {
                // wait for a connection
                Socket remote = s.accept();
                // remote is now the connected socket
                System.out.println("Connection, sending data.");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        remote.getInputStream()));
                PrintWriter out = new PrintWriter(remote.getOutputStream());

                // read the data sent. We basically ignore it,
                // stop reading once a blank line is hit. This
                // blank line signals the end of the client HTTP
                // headers.
                String str = ".";
                while (!str.equals("")) {
                    str = in.readLine();
                }

                // Send the response
                // Send the headers
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html");
                out.println("Server: Bot");
                // this blank line signals the end of the headers
                out.println("");
                // Send the HTML page
                out.println(new HighScoreController().getScoreTable());
                out.flush();
                remote.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

    }



    

    public static void main(String[] args) {
        WebServer ws = new WebServer();
        ws.start();
        AppServer as = new AppServer();
        as.start();
    }
}
