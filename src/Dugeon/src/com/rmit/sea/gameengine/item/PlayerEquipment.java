/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.MagicProperty;
import com.rmit.sea.gameengine.iteminterface.Armor;
import com.rmit.sea.gameengine.iteminterface.Equipable;
import com.rmit.sea.gameengine.iteminterface.Shield;
import com.rmit.sea.gameengine.iteminterface.Weapon;

/**
 *
 * @author gia
 */
public class PlayerEquipment implements Equipments {

    private Equipable rightArm;
    private Equipable leftArm;
    private Equipable body;

    /**
     * @param equipment the item to equip
     * @return the item that is being unequiped, put that item back to the inventory
     */
    @Override
    public Equipable equip(Equipable equipment) {

        Equipable old = null;
        if (equipment instanceof Weapon||equipment instanceof MagicProperty) {
            old=rightArm;
            rightArm = equipment;
        } else if (equipment instanceof Shield) {
            old=leftArm;
            leftArm = equipment;
        } else if (equipment instanceof Armor) {
            old=body;
            body = equipment;
        }
        return old;
    }

    @Override
    public Equipable getRightArmEquipment() {

        return rightArm;
    }

    @Override
    public Equipable getLeftArmEquipment() {
        return leftArm;
    }

    @Override
    public Equipable getBodyEquipment() {
        return body;
    }
}
