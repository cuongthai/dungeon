package com.rmit.sea.gameengine.command;

import com.rmit.sea.gameengine.behaviour.MoveBehaviour;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.GameMap;
import com.rmit.sea.gameengine.mapmodel.pixel.Door;
import com.rmit.sea.gameengine.mapmodel.pixel.DownDoor;
import com.rmit.sea.gameengine.mapmodel.pixel.UpDoor;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.model.NavigationManager;
import com.rmit.sea.view.ConsoleView;
import java.util.List;

public class MoveCommand implements Command {

    private MoveBehaviour moveBehaviour;
    private GameCharacter character;
    private boolean executed;
    private GameMap gameMap;
    private List<GameCharacter> allCharacters;

    public MoveCommand(GameCharacter character, GameMap gameMap, List<GameCharacter> allCharacters, MoveBehaviour moveBehaviour) {

        this.moveBehaviour = moveBehaviour;
        this.character = character;
        this.gameMap = gameMap;
        this.allCharacters = allCharacters;
        executed = false;
    }

    @Override
    public void execute() {

        if (moveBehaviour.getDirection() == Direction.UP
                || moveBehaviour.getDirection() == Direction.DOWN) {
            Door door = gameMap.getDoor(character.getCoordinate());
            if (door != null && moveBehaviour.getDirection() == Direction.UP && door instanceof UpDoor) {
                ((Player) character).setLevel(((Player) character).getLevel() - 1);
            } else if (door != null && moveBehaviour.getDirection() == Direction.DOWN && door instanceof DownDoor) {

                ((Player) character).setLevel(((Player) character).getLevel() + 1);
            }


        } else {
            Coordinate newCoordinate = NavigationManager.move(
                    character.getCoordinate(), moveBehaviour.getDirection(), moveBehaviour.getSteps());
            if (gameMap.isWalkable(newCoordinate)) {
                boolean isContainSmt=false;
                for (GameCharacter gameCharacter : allCharacters) {
                    if (gameCharacter.getCoordinate().equals(newCoordinate)) {
                        
                        isContainSmt=true;
                        break;
                    }
                }

                if(!isContainSmt){
                    character.setCoordinate(newCoordinate);
                }

            }
            if (character instanceof Player) {
                Player p = (Player) character;
                p.addCoordinateToLog(p.getCoordinate());
            }
            executed = true;
        }
    }

    public MoveBehaviour getMoveBehaviour() {
        return moveBehaviour;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public String toString() {
        return character.getName() + " moved " + moveBehaviour.getDirection().toString().toLowerCase();
    }
}
