/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.mapmodel.component;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import com.rmit.sea.gameengine.model.Direction;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author hoanggia
 */
public class Cavern implements MapComponent {

    private static final int offsetToCreateWall = 5;
    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> walls;
    private Map<Coordinate, ViewablePixel> outerWalls;
    private int radius;
    private ViewablePixel center;

    public Cavern(int radius) {
        this.radius = radius;
        center = new Way(new Coordinate(radius, radius));
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        viewablePixels.put(center.getCoordinate(), center);
        putNextViewablePixel(center, Direction.FORWARD);
        putNextViewablePixel(center, Direction.LEFT);
        putNextViewablePixel(center, Direction.RIGHT);
        putNextViewablePixel(center, Direction.BACKWARD);
    }

    private void putNextViewablePixel(ViewablePixel lastPixel, Direction direction) {

        Coordinate c;
        switch (direction) {
            case FORWARD:
                c = new Coordinate(lastPixel.getCoordinate().getX() + 1, lastPixel.getCoordinate().getY());
                break;
            case LEFT:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() + 1);
                break;
            case RIGHT:
                c = new Coordinate(lastPixel.getCoordinate().getX() - 1, lastPixel.getCoordinate().getY());
                break;
            default:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() - 1);
                break;
        }
        if (!viewablePixels.containsKey(c) && c.getX() >= 0 && c.getX() <= radius * 2 && c.getY() <= radius * 2 && c.getY() >= 0) {
            //ConsoleView.out("a");
            Random r = new Random();
            boolean isWay = r.nextInt(radius) > c.distanceTo(center.getCoordinate()) - offsetToCreateWall;
            if (c.distanceTo(center.getCoordinate()) == radius) {
                isWay = false;
            }
            ViewablePixel p;
            if (isWay) {
                p = new Way(c);
                viewablePixels.put(p.getCoordinate(), p);
                putNextViewablePixel(p, Direction.FORWARD);
                putNextViewablePixel(p, Direction.LEFT);
                putNextViewablePixel(p, Direction.RIGHT);
                putNextViewablePixel(p, Direction.BACKWARD);
            } else {
                p = new Wall(c);
                viewablePixels.put(p.getCoordinate(), p);
            }
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

    public Map<Coordinate, ViewablePixel> getOuterWalls() {
        if (outerWalls == null) {
            outerWalls = new HashMap<Coordinate, ViewablePixel>();

            for (ViewablePixel p : getWalls().values()) {
                Coordinate n = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1);
                Coordinate e = new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY());
                Coordinate w = new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY());
                Coordinate s = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1);
                if (!viewablePixels.containsKey(n) || !viewablePixels.containsKey(e) || !viewablePixels.containsKey(w) || !viewablePixels.containsKey(s)) {
                    outerWalls.put(p.getCoordinate(), p);
                }
            }
        }
        return outerWalls;
    }

    private int limitRangeForValue(int max, int min, int value) {
        if (max < value) {
            return max;
        }
        if (min > value) {
            return min;
        }
        return value;
    }
}
