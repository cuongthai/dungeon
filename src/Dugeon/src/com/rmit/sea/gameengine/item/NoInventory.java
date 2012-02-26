
 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import java.util.List;

/**
 *
 * @author thailycuong1202
 */
public class NoInventory implements  Inventory {

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public void setItems(List<Item> items) {

    }


    @Override
    public void addItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCurrentGold() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCurrentArrow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean useArrow(int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean useGold(int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
}
