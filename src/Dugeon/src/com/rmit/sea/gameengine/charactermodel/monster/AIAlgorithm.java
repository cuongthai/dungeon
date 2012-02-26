/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thailycuong1202
 */
public interface AIAlgorithm {

    public Command getCommand(GameCharacter computer, Player player, GameMap gameMap, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters);
}
