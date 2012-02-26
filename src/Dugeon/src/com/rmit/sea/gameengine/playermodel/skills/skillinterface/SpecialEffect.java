/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skillinterface;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hoanggia
 */
public interface SpecialEffect {

    /**
     * taking everything necessary to manipulate the game when a special effect is executed
     * @param user the player
     * @param level skill level
     * @param executeCoordinate the place where the special effect happened
     * @param gameCharacters all monster characters
     * @param fullMap the map contain monster, gameMap, item
     * @param direction will be null if this is not a range attack
     */
    public abstract void executeSpecialEffect(Attackable user, int level,
            Coordinate executeCoordinate,
            List<GameCharacter> gameCharacters,
            Map<Coordinate, ViewablePixel> fullMap, Direction direction);

}
