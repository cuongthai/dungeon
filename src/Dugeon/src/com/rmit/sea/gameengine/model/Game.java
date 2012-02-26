package com.rmit.sea.gameengine.model;

import com.rmit.sea.gameengine.charactermodel.player.PlayerLoader;
import java.util.Observer;

public interface Game{
    void createGame();
    void startGame(PlayerLoader playerLoader);
    void saveGame(boolean isSaveAndExit);
    void addObserver(Observer observer);
}
