/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thailycuong1202
 */
public class EasyCharacterFactory implements MonsterCharacterFactory {

    @Override
    public List<GameCharacter> createComputerCharacter(GameMap gameMap,
            int numberOfBoss,
            int minNumberOfMonster,
            int level) {
        //create a list of computer character in random position, and random item
        List<GameCharacter> listCharacter = new ArrayList<GameCharacter>();
        List<Coordinate> coordinateContainSomethings = new ArrayList<Coordinate>();
        Random r = new Random();
        List<Coordinate> walkableWays = gameMap.getWalkableWays();
        int mapSize = walkableWays.size();
        
        double density=Constant.MONSTERS_DENSITY;
        while (r.nextInt(100) < Constant.MONSTERS_DENSITY_INCREASE_PERCENTAGE) {
            density+=0.01;
        }

        int numberOfMonster = Math.max(minNumberOfMonster, (int)(mapSize*density));
        
        for (int i = 0; i < numberOfMonster; i++) {
            Coordinate nextCoordinate;
            do {
                nextCoordinate = walkableWays.get(
                        r.nextInt(mapSize));

            } while (coordinateContainSomethings.contains(nextCoordinate));
            coordinateContainSomethings.add(nextCoordinate);
            Monster monster = MonsterFactory.createMonster(level, nextCoordinate);

            listCharacter.add(monster);
            if (coordinateContainSomethings.size() == mapSize - 1) {
                break;
            }

        }
        return listCharacter;
    }

    @Override
    public GameCharacter createMonsterForAbuse(GameMap gameMap, Coordinate playerCo, int level) {
        Random r = new Random();
        List<Coordinate> walkableWays = gameMap.getWalkableWays();
        List<Coordinate> possiblePlace=new ArrayList<Coordinate>();
        for(Coordinate coordinate:walkableWays){
            if(!(playerCo.equals(coordinate))&&playerCo.distanceTo(coordinate)<Constant.MIN_DISTANCE_FROM_AVERAGE_POSITION){
                possiblePlace.add(coordinate);
            }
        }

        Coordinate monsterCo=possiblePlace.get(r.nextInt(possiblePlace.size()));
        return MonsterFactory.createMonster(level+5, monsterCo);


    }
}
