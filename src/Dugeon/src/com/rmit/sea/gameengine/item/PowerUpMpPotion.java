package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.PowerUpMp;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

public class PowerUpMpPotion extends Item implements Usable, PowerUpMp, Stackable, Valuable {

    private int mpUp;
    private int amount;
    private int value;

    public PowerUpMpPotion(Coordinate coordinate, int level,int amount) {
        super(coordinate);
        this.amount=amount;
        mpUp = (level / 10 + 1)*10;
        value=(level/10+1)*100;
    }

    @Override
    public int getMpUp() {
        return mpUp;
    }


    @Override
    public String getDescription() {
        return "Increase maximun MP by " + getMpUp() + " unit";
    }

    @Override
    public String getName() {
        return "Special MP Potion x "+amount;
    }


    @Override
    public boolean use(GameCharacter character, int amount) {
        if (this.amount - amount >= 0) {
            character.getCharacterDetailInfo().increaseMaxMp(mpUp * amount);
            character.getCharacterDetailInfo().increaseMp(mpUp * amount);
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
        if (item instanceof PowerUpMpPotion) {
            if (((PowerUpMpPotion) item).getMpUp() == mpUp) {
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
        return new PowerUpMpPotion(null, mpUp, 1);
    }



}
