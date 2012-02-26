package com.rmit.sea.gameengine.mapmodel.pixel;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import java.awt.Color;

public class Wall implements ViewablePixel {

    private boolean isOnSight;
    private boolean isDiscovered;
    private Coordinate coordinate;

    public Wall(Coordinate coordinate) {
        isOnSight = false;
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setOnSight(boolean onSight) {
        this.isOnSight = onSight;
    }

    @Override
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
