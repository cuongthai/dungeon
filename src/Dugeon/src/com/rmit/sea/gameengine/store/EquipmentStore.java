/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.store;

import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.item.ChainMail;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Hammer;
import com.rmit.sea.gameengine.item.HasInventory;
import com.rmit.sea.gameengine.item.Inventory;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.LeatherArmor;
import com.rmit.sea.gameengine.item.MirrorShield;
import com.rmit.sea.gameengine.item.PowerUpMpRecoveryPotion;
import com.rmit.sea.gameengine.item.Sword;
import com.rmit.sea.gameengine.item.scroll.SkillScrollFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gia
 */
public class EquipmentStore extends Store{

public EquipmentStore(Coordinate co, int level) {
        super(co, level);
        generateInventory(level);
    }

    public final void generateInventory(int level) {
        int itemLevel=(int)(level*1.5);
        List<Item> items = new ArrayList<Item>();

        Inventory inventory = new HasInventory(items);
        inventory.addItem(new Gold(0));
        inventory.addItem(new Arrow(500));
        inventory.addItem(new ChainMail(null, itemLevel));
        inventory.addItem(new ChainMail(null, itemLevel));
        inventory.addItem(new ChainMail(null, itemLevel));
        inventory.addItem(new ChainMail(null, itemLevel));
        inventory.addItem(new ChainMail(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new LeatherArmor(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new Sword(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new MirrorShield(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(new Hammer(null, itemLevel));
        inventory.addItem(SkillScrollFactory.createSkillScroll(null, itemLevel));
        inventory.addItem(SkillScrollFactory.createSkillScroll(null, itemLevel));
        inventory.addItem(SkillScrollFactory.createSkillScroll(null, itemLevel));
        inventory.addItem(SkillScrollFactory.createSkillScroll(null, itemLevel));
        inventory.addItem(SkillScrollFactory.createSkillScroll(null, itemLevel));
        inventory.addItem(new PowerUpMpRecoveryPotion(new Coordinate(0, 0)));
        super.setInventory(inventory);

    }
}
