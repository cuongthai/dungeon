package com.rmit.sea.gameengine.mapmodel.pixel;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import java.awt.Color;

public class Way implements ViewablePixel, Walkable {

    private boolean isOnSight;
    private boolean isDiscovered;
    private Coordinate coordinate;

    public Way(Coordinate coordinate) {
        isOnSight = false;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setOnSight(boolean onSight) {
        this.isOnSight = onSight;
    }

    public boolean isOnSight() {
        return isOnSight;
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
    public boolean isDiscovered() {
        return isDiscovered;
    }

    @Override
    public void setDiscovered(boolean isDiscovered) {
        this.isDiscovered = isDiscovered;
    }

    @Override
    public void setCoordinate(Coordinate c) {
        this.coordinate=c;
    }
}
