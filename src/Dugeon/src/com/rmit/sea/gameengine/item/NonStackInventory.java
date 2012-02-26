/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.Stackable;
import java.util.List;

/**
 *
 * @author gia
 */
public class NonStackInventory implements Inventory {

    private List<Item> items;
    private int gold;

    public NonStackInventory(List<Item> items) {
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
            items.add(item);

        }
    }

    @Override
    public boolean removeItem(Item item) {
        items.remove(item);
        return true;

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
