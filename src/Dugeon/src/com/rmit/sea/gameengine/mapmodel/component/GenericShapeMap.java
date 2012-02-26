/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.mapmodel.component;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gia
 */
public class GenericShapeMap implements MapComponent {

    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> walls;
    private Map<Coordinate, ViewablePixel> outerWalls;

    public GenericShapeMap(Map<Coordinate, ViewablePixel> viewablePixels) {
        this.viewablePixels = viewablePixels;
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
}
