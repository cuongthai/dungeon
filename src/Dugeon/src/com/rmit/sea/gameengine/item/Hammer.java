/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.iteminterface.HammerWeapon;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class Hammer extends Item implements HammerWeapon, Valuable {

    private Damage damage;
    private int level;
    private int value;

    public Hammer(Coordinate co, int level) {
        super(co);
        this.level = level;
        generateCharacteristic(level);
    }

    @Override
    public String getDescription() {
        return "A normal hammer. Can be used for jamming, knocking, killing ant, nailling, etc. No matter what you do, the hammer is still heavy. Damage: " + getDamage().toString();
    }

    @Override
    public String getName() {
        return generatePrefix(level, damage) + " Hammer";
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

    private void generateCharacteristic(int level) {
        Random r = new Random();
        if (level < 10) {
            this.damage = new Damage(Constant.PHYSICAL_ELEMENT, 10);
            value = 100;
        } else {
            int param = level / 10;
            if (param < 5) {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 20) + level);
                value = this.damage.getDamage() * 20;
            } else {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 40) + level);
                value = this.damage.getDamage() * 30;

            }
        }
    }

    private String generatePrefix(int level, Damage damage) {
        //blunt --- Good --- sharp --- outstanding
        int maxDamage = 0;
        int minDamage = 0;

        if (level < 10) {
            return "Stone";
        } else if (level < 50) {
            maxDamage = level / 10 * 15 - 1 + level;
            minDamage = level;

        } else {
            maxDamage = level * 2 - 1 + level;
            minDamage = level;

        }
        if (damage.getDamage() < (minDamage + (maxDamage - minDamage) / 3)) {
            return "Stone";
        } else if (damage.getDamage() < minDamage + (maxDamage - minDamage) * 2 / 3) {
            return "Iron";
        } else if (damage.getDamage() == maxDamage) {
            return "Orihalcon";
        } else {
            return "Mythril";
        }

    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int getValue() {
        return value;
    }
}
