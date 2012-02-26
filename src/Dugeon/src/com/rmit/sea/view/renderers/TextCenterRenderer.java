package com.rmit.sea.view.renderers;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.model.GameEngine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Map.Entry;
import java.util.Set;

public class TextCenterRenderer implements GameViewRenderer {

    public TextCenterRenderer() {
    }

    @Override
    public void render(GameEngine gameEngine, Graphics g, BufferStrategy strategy) {
        Font defaultFont = FontResources.getInstance().getDefaultFont(Font.PLAIN, FontResources.DEFAULT_FONT_SIZE);
        FontRenderContext fontRenderContext = new FontRenderContext(defaultFont.getTransform(), false, false);
        Rectangle2D bound = defaultFont.getStringBounds("@", fontRenderContext);
        int fontHeight = (int) bound.getHeight();
        int fontWidth = (int) bound.getWidth();
        // Calculate offset
        Coordinate offsetCoordinate = calOffset(gameEngine.getCharacterManager().getPlayer());

        /*
         * OK, do it
         */
        g.setFont(defaultFont);
        Set<Entry<Coordinate, ViewablePixel>> map = gameEngine.mergeAllViewableThing().entrySet();
        Coordinate c = null;
        ViewablePixel p = null;

        for (Entry<Coordinate, ViewablePixel> e : map) {

            c = e.getKey();
            p = e.getValue();

            renderPixel(
                    g, fontWidth, fontHeight,
                    c, p,
                    offsetCoordinate);

        }
    }

    private void renderPixel(
            Graphics g, int fontWidth, int fontHeight,
            Coordinate coordinate, ViewablePixel pixel,
            Coordinate offsetCoordinate) {

        int x = calPosition(fontWidth, offsetCoordinate.getX() + coordinate.getX());
        int y = calPosition(fontHeight, offsetCoordinate.getY() + coordinate.getY());

        if (x >= 0 && x < Constant.GAMEVIEW_WIDTH
                && y >= 0 && y < Constant.GAMEVIEW_HEIGHT
                && pixel.isDiscovered()) {

            String s = "" + pixel.getRepresentChar();

                g.setColor(pixel.getColor());

            g.drawString(s, x, y);

        }
    }

    private int calPosition(int fontSize, int i) {
        return fontSize * (i + 1);
    }

    private Coordinate calOffset(Player player) {
        int offsetX = PLAYER_CENTER_COORDINATE.getX() - player.getCoordinate().getX();
        int offsetY = PLAYER_CENTER_COORDINATE.getY() - player.getCoordinate().getY();

        return new Coordinate(offsetX, offsetY);
    }
}
