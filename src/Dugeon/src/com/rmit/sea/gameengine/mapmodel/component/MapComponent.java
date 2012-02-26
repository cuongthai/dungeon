/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.mapmodel.component;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import java.util.Map;


/**
 *
 * @author hoanggia
 */
public interface MapComponent {
    public abstract Map<Coordinate,ViewablePixel> getViewablePixels();
    public abstract Map<Coordinate,ViewablePixel> getWays();
    public abstract Map<Coordinate,ViewablePixel> getWalls();
    public abstract Map<Coordinate,ViewablePixel> getOuterWalls();



}
