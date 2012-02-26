/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.webserver;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.webserver.model.Score;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class AppServer extends Thread implements Runnable {

    @Override
    public void run() {
        ServerSocket s;

        System.out.println("Webserver starting up on port " + Constant.APP_SERVER_PORT);
        System.out.println("(press ctrl-c to exit)");
        try {
            // create the main server socket
            s = new ServerSocket(Constant.APP_SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //Init ScoreController
        HighScoreController hcController = new HighScoreController();

        System.out.println("Waiting for connection");
        ObjectInputStream inputStream = null;
        ObjectOutputStream out = null;
        Socket remote = null;
        for (;;) {
            try {
                // wait for a connection
                remote = s.accept();
                // remote is now the connected socket
                System.out.println("Connection, receiving data.");
                inputStream = new ObjectInputStream(remote.getInputStream());
                out = new ObjectOutputStream(remote.getOutputStream());
                System.out.println("Connection, receiving data. 1");
                Object o = inputStream.readObject();
                System.out.println("Object " + o);
                if (o instanceof Score) {
                    scoreProcessing(hcController, (Score) o);
                } else if (o instanceof Player) {
                    saveProcessing((Player) o);
                } else if (o instanceof String) {
                    processLoadPlayer(out, (String) o);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.out.println("App server");
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    if (remote != null) {
                        remote.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AppServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void saveProcessing(Player player) {
        ObjectOutputStream outputStream = null;
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(player.getName());
            outputStream =
                    new ObjectOutputStream(fo);
            outputStream.writeObject(player);
            outputStream.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the ObjectOutputStream
            try {

                if (outputStream != null) {
                    outputStream.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void scoreProcessing(HighScoreController hcController, Score score) {
        hcController.addHighScore(score);
        System.out.println("Added HighScore");
    }

    private void processLoadPlayer(ObjectOutputStream out, String name) {
        Player player = null;

        try {
            System.out.println("Loading user " + name + "--");
            //use buffering
            InputStream file = new FileInputStream(name);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                //deserialize the List
                player = (Player) (input.readObject());
                System.out.println("Reading File");
                out.writeObject(player);
                out.flush();
                System.out.println("End Loading User");
            } finally {
                if (input != null) {
                    input.close();
                }
                if (buffer != null) {
                    buffer.close();
                }
                if (file != null) {
                    file.close();
                }

            }
        } catch (ClassNotFoundException ex) {
            player = null;
        } catch (IOException ex) {
            player = null;
        } catch (Exception ex) {
            player = null;
        }
    }
}
