/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.mapmodel.component;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

/**
 *
 * @author hoanggia
 */
public class Corridor implements MapComponent {

    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> walls;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> outerWalls;
    private boolean isVectical;
    private Coordinate from, to;

    public Corridor(Coordinate from, Coordinate to) {
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        this.from = from;
        this.to = to;
        generateWays();
        generateWalls();
    }

    private void generateWays() {
        int xOffset = from.getX() - to.getX();
        int yOffset = from.getY() - to.getY();
        ViewablePixel fromPixel = new Way(from);
        ViewablePixel toPixel = new Way(to);
        viewablePixels.put(fromPixel.getCoordinate(), fromPixel);
        viewablePixels.put(toPixel.getCoordinate(), toPixel);

        while (xOffset != 0 || yOffset != 0) {
            Random r = new Random();
            boolean isX = r.nextBoolean();
            Coordinate c;
            if (isX && xOffset != 0) {
                xOffset -= 1 * (xOffset > 0 ? 1 : -1);
            } else {
                if (yOffset != 0) {
                    yOffset -= 1 * (yOffset > 0 ? 1 : -1);
                }
            }
            c = new Coordinate(from.getX() - xOffset, from.getY() - yOffset);
            ViewablePixel p = new Way(c);
            viewablePixels.put(p.getCoordinate(), p);
        }


    }

    private void generateWalls() {
        Map<Coordinate, ViewablePixel> waysNow = new HashMap<Coordinate, ViewablePixel>(getWays());
        for (ViewablePixel p : waysNow.values()) {

            if (p instanceof Way && !p.getCoordinate().equals(from) && !p.getCoordinate().equals(to)) {
                if (!viewablePixels.containsKey(new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY()))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY()));
                    viewablePixels.put(wall.getCoordinate(), wall);

                }
                if (!viewablePixels.containsKey(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1));
                    viewablePixels.put(wall.getCoordinate(), wall);
                }
                if (!viewablePixels.containsKey(new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY()))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY()));
                    viewablePixels.put(wall.getCoordinate(), wall);
                }
                if (!viewablePixels.containsKey(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1));
                    viewablePixels.put(wall.getCoordinate(), wall);
                }

            }

//            if (p.getCoordinate().equals(from)) {
//            }
        }
    }

    public Map<Coordinate, ViewablePixel> getViewablePixels() {
        return viewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWays() {
        if (ways == null) {
            ways = new HashMap<Coordinate, ViewablePixel>();
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Way) {
                    ways.put(p.getCoordinate(), p);
                }
            }
        }
        return ways;
    }

    public Map<Coordinate, ViewablePixel> getWalls() {
        if (walls == null) {
            walls = new HashMap<Coordinate, ViewablePixel>();
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Wall) {
                    walls.put(p.getCoordinate(), p);
                }
            }
        }

        return walls;
    }

    @Override
    public Map<Coordinate, ViewablePixel> getOuterWalls() {
        if (outerWalls == null) {
            outerWalls = new HashMap<Coordinate, ViewablePixel>();
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Wall) {
                    outerWalls.put(p.getCoordinate(), p);
                }
            }
        }
        return outerWalls;
    }
}
