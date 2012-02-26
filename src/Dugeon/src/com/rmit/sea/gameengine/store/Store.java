/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.store;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import java.awt.Color;

/**
 *
 * @author Magickey
 */
public abstract class Store implements Walkable, ViewablePixel {

    private Coordinate co;
    private int level;
    private boolean isOnSight;
    private boolean isDiscover;
    private Inventory inventory;

    public Store(Coordinate co, int level) {
        this.co = co;
        this.level = level;

    }

    public int getLevel() {
        return level;
    }

    @Override
    public Coordinate getCoordinate() {
        return co;
    }

    @Override
    public void setCoordinate(Coordinate c) {
        this.co = c;
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
        this.isOnSight = isOnSight;
    }

    @Override
    public boolean isOnSight() {
        return isOnSight;
    }

    @Override
    public void setDiscovered(boolean isDiscovered) {
        this.isDiscover = isDiscovered;
    }

    @Override
    public boolean isDiscovered() {
        return isDiscover;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
