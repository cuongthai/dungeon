/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.iteminterface.Equipable;
import java.io.Serializable;

/**
 *
 * @author gia
 */
public interface Equipments extends Serializable{

    Equipable equip(Equipable equipment);

    Equipable getRightArmEquipment();

    Equipable getLeftArmEquipment();

    Equipable getBodyEquipment();
}
