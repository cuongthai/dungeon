/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.view.ConsoleView;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author hoanggia
 */
public abstract class Item implements Walkable, ViewablePixel, Serializable {

    private boolean isDiscovered;
    private Coordinate co;
    private boolean onSight;
    public Item(Coordinate co){
        this.co=co;
        onSight=false;
    }



    public abstract String getDescription();

    public abstract String getName();

    @Override
    public final void setDiscovered(boolean isDiscovered) {
        ConsoleView.out("------set: " + isDiscovered);
        this.isDiscovered = isDiscovered;
    }

    @Override
    public final boolean isDiscovered() {
        ConsoleView.out("------get: " + isDiscovered);
        return isDiscovered;
    }

    @Override
    public Coordinate getCoordinate() {
        return co;
    }

    @Override
    public void setCoordinate(Coordinate c){
        this.co=c;
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
    public void setOnSight(boolean isOnSight) {
        onSight=isOnSight;
    }

    @Override
    public boolean isOnSight() {
        return onSight;
    }

}
