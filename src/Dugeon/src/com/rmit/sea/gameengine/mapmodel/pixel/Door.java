package com.rmit.sea.gameengine.mapmodel.pixel;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import java.awt.Color;

public abstract class Door implements ViewablePixel, Walkable {

    private boolean isOnSight;
    private Coordinate coordinate;
    private boolean isDown;
    private boolean isDiscovered;

    public Door(Coordinate coordinate) {
        isOnSight = false;
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate c) {
        this.coordinate=c;
    }

    @Override
    public void setOnSight(boolean isOnSight) {
        this.isOnSight = isOnSight;
    }

    @Override
    public boolean isOnSight() {
        return isOnSight;
    }

    @Override
    public boolean isDiscovered() {
        return isDiscovered;
    }

    @Override
    public Color getColor() {
        return ColorResources.getColorFor(this);
    }

    @Override
    public char getRepresentChar() {
        return CharacterResources.getCharFor(this);
    }
    
    @Override
    public void setDiscovered(boolean isDiscovered) {
        this.isDiscovered = isDiscovered;
    }
}
