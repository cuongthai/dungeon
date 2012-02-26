/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.player;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.player.Player;

/**
 *
 * @author thailycuong1202
 */
public interface PlayerLoader {


    Player getPlayer(Coordinate coordinate);

    boolean isSuccess();
}
