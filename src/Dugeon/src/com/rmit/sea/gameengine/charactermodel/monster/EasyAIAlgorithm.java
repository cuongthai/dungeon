/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.gameengine.behaviour.AIBehaviourFactory;
import com.rmit.sea.gameengine.behaviour.BehaviourFactory;
import com.rmit.sea.gameengine.behaviour.MoveBehaviour;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.MoveCommand;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.view.ConsoleView;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author thailycuong1202
 */
public class EasyAIAlgorithm implements AIAlgorithm {

    private GameCharacter player;

    public EasyAIAlgorithm() {
        //It need to know should it attack if doesnt have any character near it
    }

    @Override
    public Command getCommand(GameCharacter computer,Player player,GameMap gameMap,Map<Coordinate,ViewablePixel> fullMap,List<GameCharacter> allCharacters) {
        this.player=player;
        Command command;
        Coordinate playerCoordinate = player.getCoordinate();
        Coordinate computerCoordinate = computer.getCoordinate();
        BehaviourFactory actionFactory = new AIBehaviourFactory();

        //if the player is near computer, use its skill to attack
        if (isAround(computerCoordinate, playerCoordinate)) {
            command = new AttackCommand(actionFactory.createAttackBehaviour(computer,
                    fullMap,
                    allCharacters,
                    playerCoordinate));

        } else if (isInActivationRange(computer,
                //check distance of computer vs player, if yes move toward
                player)) {
            MoveBehaviour moveOne = actionFactory.createMoveBehaviour(
                    calculateDirection(computerCoordinate, playerCoordinate),
                    1);

            command = new MoveCommand(computer,gameMap,allCharacters, moveOne);
        } else {
            //Any thing else, move random
            MoveBehaviour moveRandomBehaviour = actionFactory.createMoveBehaviour(null, 1);
            command = new MoveCommand(computer,gameMap,allCharacters, moveRandomBehaviour);
        }
        return command;

    }

    private Direction calculateDirection(Coordinate comCo, Coordinate playerCo) {
        int offsetX = Math.abs(comCo.getX() - playerCo.getX());
        int offsetY = Math.abs(comCo.getY() - playerCo.getY());
        if (offsetX > offsetY) {
            if (comCo.getX() - playerCo.getX() > 0) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        } else if (offsetX < offsetY) {
            if (comCo.getY() - playerCo.getY() > 0) {
                return Direction.FORWARD;
            } else {
                return Direction.BACKWARD;
            }
        } else {
            Random r = new Random();
            if (r.nextBoolean()) {
                if (comCo.getY() - playerCo.getY() > 0) {
                    return Direction.FORWARD;
                } else {
                    return Direction.BACKWARD;
                }
            } else {
                if (comCo.getX() - playerCo.getX() > 0) {
                    return Direction.LEFT;
                } else {
                    return Direction.RIGHT;
                }
            }
        }

    }

    private boolean isInActivationRange(GameCharacter monster, GameCharacter player) {
        
        try {
            return (monster.getActivationDistance().getCoordinatesOnSight().contains(player.getCoordinate()));
        } catch (Exception ex) {
            return false;
        }

    }

    private boolean isAround(Coordinate coordinate, Coordinate playerCoordinate) {
        if (coordinate.distanceTo(playerCoordinate) == 1) {
            if (coordinate.getX() == playerCoordinate.getX() || coordinate.getY() == playerCoordinate.getY()) {
                return true;
            }

        }
        return false;
    }
}
