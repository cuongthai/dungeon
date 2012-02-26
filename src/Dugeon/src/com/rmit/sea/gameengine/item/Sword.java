/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.iteminterface.SwordWeapon;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class Sword extends Item implements SwordWeapon,Valuable {

    private Damage damage;
    private int level;
    private int value;

    public Sword(Coordinate co, int level) {
        super(co);
        this.level=level;
        generateCharacteristic(level);
    }

    @Override
    public String getDescription() {
        return "A normal sword. Can be used for attacking, slashing, cutting, shaving bear, preparing meal etc. Beware! Have sharp edge. Damage: "+getDamage().toString();
    }

    @Override
    public String getName() {
        return generatePrefix(level, damage)+" Sword";
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

    private void generateCharacteristic(int level) {
        Random r = new Random();
        if (level < 10) {
            this.damage = new Damage(Constant.PHYSICAL_ELEMENT, 5);
            value=90;
        } else {
            int param = level / 10;
            if (param < 5) {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 15) + level);
                value=this.damage.getDamage()*15;
            } else {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 30) + level);
                value=this.damage.getDamage()*25;
            }
        }
    }

    private String generatePrefix(int level, Damage damage) {
        //blunt --- Good --- sharp --- outstanding
        int maxDamage = 0;
        int minDamage = 0;

        if (level < 10) {
            return "Good";
        } else if (level < 50) {
            maxDamage = level - 1 + level;
            minDamage = level;

        } else {
            maxDamage = level / 10 * 15 - 1 + level;
            minDamage = level;

        }
        if (damage.getDamage() < (minDamage + (maxDamage - minDamage) / 3)) {
            return "Blunt";
        } else if (damage.getDamage() < minDamage + (maxDamage - minDamage) * 2 / 3) {
            return "Good";
        } else if (damage.getDamage() == maxDamage) {
            return "Legendary";
        } else {
            return "Sharp";
        }

    }

    @Override
    public int getValue() {
        return value;
    }

}
