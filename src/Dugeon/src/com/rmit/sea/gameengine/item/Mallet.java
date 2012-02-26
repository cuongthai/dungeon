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
public class Mallet extends Item implements HammerWeapon, Valuable {

    private Damage damage;
    private int level;
    private int value;

    public Mallet(Coordinate co, int level) {
        super(co);
        this.level = level;
        generateCharacteristic(level);
    }

    @Override
    public String getDescription() {
        return "Bigger than hammer. Can be used to stick stuffs together. Can also stick your head to the ground if not careful. Damage: " + getDamage().toString();
    }

    @Override
    public String getName() {
        return generatePrefix(level, damage) + " Mallet";
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

    private void generateCharacteristic(int level) {
        Random r = new Random();
        if (level < 10) {
            this.damage = new Damage(Constant.PHYSICAL_ELEMENT, 20);
            value = 100;
        } else {
            int param = level / 10;
            if (param < 5) {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 25) + level);
                value = this.damage.getDamage() * 40;
            } else {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 50) + level);
                value = this.damage.getDamage() * 60;

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

