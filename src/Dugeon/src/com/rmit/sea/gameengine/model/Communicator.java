/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.model;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Steve
 */
public class Communicator {

    /**
     * check if the username is existed in the server or not
     * @param name
     * @return
     */
    public boolean isExisted(String name) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {

            System.out.println("Sending " + name);
            //Open Socket
            socket = new Socket(Constant.SERVER_ADDRESS, Constant.APP_SERVER_PORT);


            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeObject(name);
            outputStream.flush();
            Player p = (Player) inputStream.readObject();
            if (p != null) {
                return true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {

                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /**
     * send score to the server
     * @param score
     */
    public void send(Object score) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        try {

            System.out.println("Sending");
            //Open Socket
            socket = new Socket(Constant.SERVER_ADDRESS, Constant.APP_SERVER_PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(score);
            outputStream.flush();
            outputStream.close();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * get the player from the server by providing username and password
     * @param name
     * @param password
     * @return
     */
    public Player send(String name, String password) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {

            System.out.println("Sending " + name);
            //Open Socket
            socket = new Socket(Constant.SERVER_ADDRESS, Constant.APP_SERVER_PORT);


            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeObject(name);
            outputStream.flush();
            Player p = (Player) inputStream.readObject();
            if (p.getPassword().equals(password)) {
                return p;
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {

                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
