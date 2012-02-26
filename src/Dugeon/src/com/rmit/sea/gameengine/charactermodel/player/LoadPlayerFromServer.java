/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.player;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.Communicator;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author gia
 */
public class LoadPlayerFromServer implements PlayerLoader {

    private Player player;

    public LoadPlayerFromServer(String name,String password) {
        Communicator communicator = new Communicator();
        player = communicator.send(name,password);


    }

    @Override
    public Player getPlayer(Coordinate coordinate) {
        if (player != null) {
            player.setCoordinate(coordinate);
        }
        return player;
    }

    @Override
    public boolean isSuccess() {
        return player != null&& player.isAlive();
    }
}
