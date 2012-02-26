package com.rmit.sea.gameengine.charactermodel;

import com.rmit.sea.gameengine.charactermodel.monster.Monster;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class CharacterManager {

    private Player player;
    private List<GameCharacter> computerCharacters;
    private static final Object lock = new Object();

    public CharacterManager(Player player, List<GameCharacter> computerCharacters) {
        this.player = player;
        synchronized (lock) {
            this.computerCharacters = computerCharacters;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<GameCharacter> getComputerCharacters() {
        return computerCharacters;
    }

    public void addNewComputerCharacters(List<GameCharacter> characters) {
        synchronized (lock) {
            computerCharacters.addAll(characters);
        }
    }

    public List<GameCharacter> getAllGameCharacters() {
        List<GameCharacter> gameCharacters = new ArrayList<GameCharacter>(getComputerCharacters());
        gameCharacters.add(player);
        return gameCharacters;
    }

    public void setPlayer(Player playerUnderTest) {
        this.player = playerUnderTest;
    }

    public boolean isContainACharacter(Coordinate co) {

        return false;
    }

    public void removeDeadCharacters(Inventory mapInventory, int level) {
        List<GameCharacter> characters = new ArrayList<GameCharacter>();

        synchronized (lock) {
            for (GameCharacter c : computerCharacters) {
                if (c.getCharacterDetailInfo().getHp() > 0) {
                    characters.add(c);
                } else {
                    if (c instanceof Monster) {
                        Coordinate co = c.getCoordinate();
                        Monster m = (Monster) c;
                        mapInventory.addItem(m.getItemFactory().createItem(co, level));
                    }
                }
            }

            computerCharacters.clear();
            computerCharacters.addAll(characters);
        }
    }
}
