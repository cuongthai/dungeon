/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.ArrayList;

import java.util.List;

/**
 * Modelling the list of inventory that game characters carrying (Player, boss,  monster...) except NPC
 * @author thailycuong1202
 */
public class HasInventory implements Inventory {

    private List<Item> items;
    private int gold;

    public HasInventory(List<Item> items) {
        this.items = items;
        
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public void addItem(Item item) {
        
        if (item != null) {
            if (item instanceof Stackable) {
                for (Item stack : items) {
                    if (stack instanceof Stackable && ((Stackable) stack).equals(item)) {
                        ((Stackable) stack).merge((Stackable) item);
                        return;
                    }
                }
                items.add(item);

            } else {
                items.add(item);
            }
        }
    }


    @Override
    public boolean removeItem(Item item) {
        if (item instanceof Stackable) {
            if(((Stackable)item).getAmount()==0){
                items.remove(item);
                return true;
            }else{
                return false;
            }
        } else {
            items.remove(item);
            return true;
        }

    }

    @Override
    public int getCurrentGold() {
        for (Item item : items) {
            if (item instanceof Gold) {
                return ((Gold) item).getAmount();
            }
        }
        return 0;
    }

    @Override
    public int getCurrentArrow() {
        for (Item item : items) {
            if (item instanceof Arrow) {
                return ((Arrow) item).getAmount();
            }
        }
        return 0;
    }

    @Override
    public boolean useArrow(int amount) {
        for (Item item : items) {
            if (item instanceof Arrow) {
                return ((Arrow) item).use(null, amount);
            }
        }
        return false;
    }

    @Override
    public boolean useGold(int amount) {
        for (Item item : items) {
            if (item instanceof Gold) {
                return ((Gold) item).use(null, amount);
            }
        }
        return false;
    }


}
