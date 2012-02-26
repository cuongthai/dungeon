package com.rmit.sea.view.renderers;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.dungeon.resources.FontResources;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import com.rmit.sea.gameengine.model.GameEngine;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GraphicCenterRenderer implements GameViewRenderer {

    private BufferedImage player, wall, way;

    public GraphicCenterRenderer() {
        try {
            player = ImageIO.read(new File(Constant.RES_DIR + "player.jpg"));
            wall = ImageIO.read(new File(Constant.RES_DIR + "wall.png"));
            way = ImageIO.read(new File(Constant.RES_DIR + "way.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicCenterRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameEngine gameEngine, Graphics g,BufferStrategy strategy) {
        Font defaultFont = FontResources.getInstance().getDefaultFont(Font.PLAIN, 20f);
        FontRenderContext fontRenderContext = new FontRenderContext(defaultFont.getTransform(), false, false);
        Rectangle2D bound = defaultFont.getStringBounds("X", fontRenderContext);
        int fontHeight = 15;
        int fontWidth = 15;
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


            if (pixel instanceof Player) {
                g.drawImage(player, x, y, 15, 15, null);
            } else if (pixel instanceof Wall) {
                g.drawImage(wall, x, y, 15, 15, null);
            } else if (pixel instanceof Walkable) {
                g.drawImage(way, x, y, 15, 15, null);
            } else {
                g.setColor(pixel.getColor());
                String s = "" + pixel.getRepresentChar();
                g.drawString(s, x, y);
            }
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
