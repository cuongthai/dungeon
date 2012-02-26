/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.item;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.iteminterface.BowWeapon;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class Bow  extends Item implements BowWeapon,Valuable {

    private Damage damage;
    private int level;
    private int value;

    public Bow(Coordinate co, int level) {
        super(co);
        this.level=level;
        generateCharacteristic(level);
    }

    @Override
    public String getDescription() {
        return "A normal bow. Can be used for shooting arrow. Experts can use this to play guitar. Damage: "+getDamage().toString();
    }

    @Override
    public String getName() {
        return generatePrefix(level, damage)+" Bow";
    }

    @Override
    public Damage getDamage() {
        return damage;
    }

    private void generateCharacteristic(int level) {
        Random r = new Random();
        if (level < 10) {
            this.damage = new Damage(Constant.PHYSICAL_ELEMENT, 3);
            value=100;
        } else {
            int param = level / 10;
            if (param < 5) {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 5) + level);
                value=this.damage.getDamage()*20;
            } else {
                this.damage = new Damage(Constant.PHYSICAL_ELEMENT, r.nextInt(param * 10) + level);
                value=this.damage.getDamage()*30;
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
            maxDamage = level/2 - 1 + level;
            minDamage = level;

        } else {
            maxDamage = level - 1 + level;
            minDamage = level;

        }
        if (damage.getDamage() < (minDamage + (maxDamage - minDamage) / 3)) {
            return "Paper";
        } else if (damage.getDamage() < minDamage + (maxDamage - minDamage) * 2 / 3) {
            return "Wooden";
        } else if (damage.getDamage() == maxDamage) {
            return "Compound";
        } else {
            return "Aluminum";
        }

    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public int getValue() {
        return value;
    }
}
