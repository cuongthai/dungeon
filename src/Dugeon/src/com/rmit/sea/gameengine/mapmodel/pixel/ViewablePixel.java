package com.rmit.sea.gameengine.mapmodel.pixel;

import java.awt.Color;

public interface ViewablePixel {

    Coordinate getCoordinate();

    void setCoordinate(Coordinate c);

    Color getColor();

    char getRepresentChar();

    void setOnSight(boolean isOnSight);

    boolean isOnSight();
    
    void setDiscovered(boolean isDiscovered);

    boolean isDiscovered();
}
