/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.iteminterface.Pickable;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.Unusable;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

/**
 *
 * @author gia
 */
public class Arrow extends Item implements Usable, Pickable, Stackable, Valuable {

    private int amount;
    private int value;

    public Arrow(Coordinate coordinate, int level) {
        super(coordinate);
        amount = generateAmountOfArrowByLevel(level);
        value = 5;
    }

    public Arrow(int amount) {
        super(null);
        this.amount = amount;
        value = 5;
    }

    private int generateAmountOfArrowByLevel(int level) {
        return (level / 10 + 1) * 10;
    }

    @Override
    public String getDescription() {
        return "Use for shooting. Can also be used for floss your teeth.";
    }

    @Override
    public String getName() {
        return "Arrow x " + amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Item item) {
        return (item instanceof Arrow);

    }

    @Override
    public void merge(Stackable item) {
        this.amount += item.getAmount();

    }

    @Override
    public boolean use(GameCharacter character, int amount) {
        if (this.amount < amount) {
            return false;
        } else {
            this.amount -= amount;
            return true;
        }
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Item getOneItem() {
        return new Arrow(1);
    }

    @Override
    public int getValue() {
        return value;
    }
}
