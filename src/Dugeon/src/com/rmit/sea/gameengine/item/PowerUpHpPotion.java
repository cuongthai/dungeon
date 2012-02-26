/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.PowerUpHp;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

/**
 *
 * @author hoanggia
 */
public class PowerUpHpPotion extends Item implements Usable, PowerUpHp, Stackable,Valuable {

    private int hpUp;
    private int amount;
    private int value;

    public PowerUpHpPotion(Coordinate coordinate, int level, int amount) {
        super(coordinate);
        this.amount = amount;
        hpUp = (level / 10 + 1) * 10;
        value=(level/10+1)*100;

    }

    @Override
    public int getHpUp() {
        return hpUp;
    }

    @Override
    public String getDescription() {
        return "Increase maximun HP by " + getHpUp() + " unit";
    }

    @Override
    public String getName() {
        return "Special HP Potion x "+amount;
    }

    @Override
    public boolean use(GameCharacter character, int amount) {
        if (this.amount - amount >= 0) {
            character.getCharacterDetailInfo().increaseMaxHp(hpUp * amount);
            character.getCharacterDetailInfo().increaseHp(hpUp * amount);
            this.amount -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Item item) {
        if (item instanceof PowerUpHpPotion) {
            if (((PowerUpHpPotion) item).getHpUp() == hpUp) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void merge(Stackable item) {
        this.amount += item.getAmount();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setAmount(int amount) {
        this.amount=amount;
    }

    @Override
    public Item getOneItem() {
        return new PowerUpHpPotion(null, hpUp, 1);
    }

}
