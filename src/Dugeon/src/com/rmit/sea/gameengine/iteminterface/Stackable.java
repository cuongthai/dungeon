/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.iteminterface;

import com.rmit.sea.gameengine.item.Item;

/**
 *
 * @author gia
 */
public interface Stackable {
    int getAmount();

    void setAmount(int amount);

    boolean equals(Item item);

    void merge(Stackable item);

    Item getOneItem();




}
