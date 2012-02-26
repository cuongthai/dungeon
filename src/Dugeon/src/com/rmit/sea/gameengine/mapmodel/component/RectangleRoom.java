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

/**
 *
 * @author hoanggia
 */
public class RectangleRoom implements MapComponent {

    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> walls;
    private Map<Coordinate, ViewablePixel> outerWalls;
    private int width;
    private int height;

    public RectangleRoom(int width, int height) {
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        this.width = width;
        this.height = height;
        generatePixels();
    }

    private void generatePixels() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinate c = new Coordinate(j, i);
                ViewablePixel p;
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    p = new Wall(c);
                } else {
                    p = new Way(c);
                }
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
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Wall) {
                    outerWalls.put(p.getCoordinate(), p);
                }
            }
        }
        return outerWalls;
    }
}
