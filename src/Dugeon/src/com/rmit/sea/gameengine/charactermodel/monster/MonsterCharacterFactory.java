/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.List;

/**
 *
 * @author thailycuong1202F
 */
public interface MonsterCharacterFactory {

    public List<GameCharacter> createComputerCharacter(
            GameMap gameMap,
            int numberOfBoss,
            int numberOfMonster,
            int level);

    public GameCharacter createMonsterForAbuse(GameMap gameMap,Coordinate playerCo,int level);


}
