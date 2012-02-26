/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.store;

import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.HasInventory;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magickey
 */
public class PotionStore extends Store {

    public PotionStore(Coordinate co, int level) {
        super(co, level);
        generateInventory(level);
    }

    public final void generateInventory(int level) {
        List<Item> items = new ArrayList<Item>();

        Inventory inventory = new HasInventory(items);
        inventory.addItem(new Gold(0));
        inventory.addItem(new HpPotion(new Coordinate(0, 0), level * 2, 100));
        inventory.addItem(new MpPotion(new Coordinate(0, 0), level * 2, 100));
        inventory.addItem(new PowerUpMpPotion(new Coordinate(0, 0), level, 1));
        inventory.addItem(new PowerUpHpPotion(new Coordinate(0, 0), level, 1));
        super.setInventory(inventory);

    }
}
