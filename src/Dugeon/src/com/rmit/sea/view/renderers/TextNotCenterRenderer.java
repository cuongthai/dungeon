package com.rmit.sea.view.renderers;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.model.GameEngine;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Map.Entry;

/**
 * This class will receive a map and a list of character.
 * Then, renders a part of it to the view
 */
public class TextNotCenterRenderer implements GameViewRenderer {

    @Override
    public void render(GameEngine gameEngine, Graphics g, BufferStrategy strategy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    private static final Font DEFAULT_FONT = new Font("Courier New", Font.PLAIN, 20);
//    private static final int HEIGHT_OFFSET = 7;
//    private CharacterResources characterResources;
//    private ColorResources colorResources;
//
//    public TextNotCenterRenderer(CharacterResources characterResources, ColorResources colorResources) {
//        this.characterResources = characterResources;
//        this.colorResources = colorResources;
//    }
//
//    @Override
//    public void render(GameEngine gameEngine, Graphics g,BufferStrategy strategy) {
//        g.setFont(DEFAULT_FONT);
//
//        FontRenderContext fontRenderContext = new FontRenderContext(DEFAULT_FONT.getTransform(), false, false);
//        Rectangle2D bound = DEFAULT_FONT.getStringBounds(".", fontRenderContext);
//        int fontWidth = (int) bound.getWidth();
//        int fontHeight = (int) bound.getHeight();
//
//        // Calculate offset
//        Coordinate offsetCoordinate = calOffset(gameEngine.getCharacterManager().getPlayer().getCoordinate(),
//                fontWidth, fontHeight,
//                Constant.GAMEVIEW_WIDTH, Constant.GAMEVIEW_HEIGHT);
//
//        Coordinate c = null;
//        ViewablePixel p = null;
//       for (Entry<Coordinate, ViewablePixel> e : gameEngine.mergeAllPixels().entrySet()) {
//            c = e.getKey();
//            p = e.getValue();
//
//            renderPixel(
//                    g, fontWidth, fontHeight,
//                    characterResources, colorResources,
//                    c, p,
//                    offsetCoordinate);
//        }
//
//    }
//
//    private void renderPixel(
//            Graphics g, int fontWidth, int fontHeight,
//            CharacterResources characterResources, ColorResources colorResources,
//            Coordinate coordinate, ViewablePixel pixel,
//            Coordinate offsetCoordinate) {
//
//        int x = calPosition(fontWidth, coordinate.getX()) - offsetCoordinate.getX();
//        int y = calPosition(fontHeight, coordinate.getY()) - offsetCoordinate.getY();
//
//        if (x >= 0 && x < Constant.GAMEVIEW_WIDTH
//                && y >= 0 && y < Constant.GAMEVIEW_HEIGHT) {
//            String s = "" + characterResources.getCharFor(pixel);
//            g.setColor(colorResources.getColorFor(pixel));
//            g.drawString(s, x, y);
//        }
//    }
//
//    private int calPosition(int fontSize, int i) {
//        return fontSize * (i + 1);
//    }
//
//    private Coordinate calOffset(Coordinate rootCoordinate,
//            int fontWidth, int fontHeight,
//            int width, int height) {
//
//        int offsetX = 0, offsetY = 0;
//        if (isOutOfView(rootCoordinate, width, height)) {
//            offsetX = (calPosition(fontWidth, rootCoordinate.getX()) / width) * width;
//            offsetY = (calPosition(fontHeight, rootCoordinate.getY()) / height) * height;
//        }
//
//        return new Coordinate(offsetX, offsetY);
//    }
//
//    private boolean isOutOfView(Coordinate coordinate, int fontWidth, int fontHeight) {
//        return (calPosition(fontWidth, coordinate.getX()) > Constant.GAMEVIEW_WIDTH
//                || calPosition(fontHeight, coordinate.getY()) > Constant.GAMEVIEW_HEIGHT);
//    }
}
