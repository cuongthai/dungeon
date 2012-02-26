/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.item;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.iteminterface.Armor;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class LeatherArmor extends Item implements Armor,Valuable{
    private int defense;
    private int value;

    public LeatherArmor(Coordinate co,int level){
        super(co);
        generateCharacteristic(level);
    }

    @Override
    public String getDescription() {
        return "Armor made of leather. Can repel arrow and stuff. Make you feel a little bit safe. Defense: "+getDefense();
    }

    @Override
    public String getName() {
        return "Leather armor";
    }

    @Override
    public int getDefense() {
        return defense;
    }

    private void generateCharacteristic(int level) {
        this.defense=level/10+1;
        value=defense*30;
    }

    @Override
    public int getValue() {
        return value;
    }

}
