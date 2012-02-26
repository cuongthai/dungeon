package com.rmit.sea.view.renderers;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.GameEngine;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public interface GameViewRenderer {

    Coordinate PLAYER_CENTER_COORDINATE = new Coordinate(25, 15);

    void render(GameEngine gameEngine, Graphics g,BufferStrategy strategy);
}
