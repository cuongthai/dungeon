/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.gameengine.behaviour.AIBehaviourFactory;
import com.rmit.sea.gameengine.behaviour.BehaviourFactory;
import com.rmit.sea.gameengine.behaviour.MoveBehaviour;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.command.AttackCommand;
import com.rmit.sea.gameengine.command.Command;
import com.rmit.sea.gameengine.command.MoveCommand;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.model.NavigationManager;
import com.rmit.sea.view.ConsoleView;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author gia
 */
public class MediumAIAlgorithm implements AIAlgorithm {

    private GameCharacter player;

    public MediumAIAlgorithm() {
    }

    @Override
    public Command getCommand(GameCharacter computer,Player player,GameMap gameMap,Map<Coordinate,ViewablePixel> fullMap,List<GameCharacter> allCharacters) {
        this.player=player;
        //It need to know should it attack if doesnt have any character near it
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
                    calculateDirection(computerCoordinate, playerCoordinate, gameMap),1);

            command = new MoveCommand(computer, gameMap, allCharacters, moveOne);
           
        } else {
            //Any thing else, move random
            MoveBehaviour moveRandomBehaviour = actionFactory.createMoveBehaviour(null, 1);
            command = new MoveCommand(computer, gameMap, allCharacters, moveRandomBehaviour);
           
        }
        return command;

    }

    private Direction calculateDirection(Coordinate comCo, Coordinate playerCo, GameMap gameMap) {
        int offsetX = Math.abs(comCo.getX() - playerCo.getX());
        int offsetY = Math.abs(comCo.getY() - playerCo.getY());
        Direction leftOrRight = calculateLeftOrRight(comCo, playerCo);
        Direction forwardOrBackward = calculateForwardOrBackward(comCo, playerCo);
        if (offsetX > offsetY) {
            Coordinate newCo = NavigationManager.move(comCo, leftOrRight, 1);
            if (gameMap.isWalkable(newCo)) {
                return leftOrRight;
            } else {
                return forwardOrBackward;
            }
        } else if (offsetX < offsetY) {
            Coordinate newCo = NavigationManager.move(comCo, forwardOrBackward, 1);
            if (gameMap.isWalkable(newCo)) {
                return forwardOrBackward;
            } else {
                return leftOrRight;
            }
        } else {
            Random r = new Random();
            Coordinate newCo;
            switch (r.nextInt(2)) {
                case 0:
                    newCo = NavigationManager.move(comCo, forwardOrBackward, 1);
                    if (gameMap.isWalkable(newCo)) {
                        return forwardOrBackward;
                    } else {
                        return leftOrRight;
                    }

                case 1:
                    newCo = NavigationManager.move(comCo, leftOrRight, 1);
                    if (gameMap.isWalkable(newCo)) {
                        return leftOrRight;
                    } else {
                        return forwardOrBackward;
                    }
                default:
                    return leftOrRight;

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

    private Direction calculateLeftOrRight(Coordinate comCo, Coordinate playerCo) {
        if (comCo.getX() - playerCo.getX() > 0) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }

    private Direction calculateForwardOrBackward(Coordinate comCo, Coordinate playerCo) {
        if (comCo.getY() - playerCo.getY() > 0) {
            return Direction.FORWARD;
        } else {
            return Direction.BACKWARD;
        }
    }
}
