/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

/**
 *
 * @author gia
 */
public class PowerUpMpRecoveryPotion extends Item implements Usable,Valuable {
    private int value;

    public PowerUpMpRecoveryPotion(Coordinate co) {
        super(co);
        value=1000;

    }

    @Override
    public String getDescription() {
        return "Increase MP recovery amount.";
    }

    @Override
    public String getName() {
        return "Special MP Recovery Potion";
    }

    @Override
    public boolean use(GameCharacter character, int amount) {
        if(character instanceof Player){
            Player p=(Player)character;
            p.setMpRecovery(p.getMpRecovery()+1);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getValue() {
        return value;
    }
}
