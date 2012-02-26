/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Item;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thailycuong1202
 */
public interface Inventory extends Serializable{

    int getCurrentGold();

    int getCurrentArrow();

    boolean useArrow(int amount);

    boolean useGold(int amount);

    List<Item> getItems();

    void setItems(List<Item> items);

    void addItem(Item item);

    boolean removeItem(Item item);
}
