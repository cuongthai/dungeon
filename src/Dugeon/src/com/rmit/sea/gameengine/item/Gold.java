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
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *
 * @author gia
 */
public class Gold extends Item implements Usable,Pickable,Stackable,Unusable {

    private int amount;

    public Gold(Coordinate coordinate, int level) {
        super(coordinate);
        amount = generateAmountOfGoldByLevel(level);

    }
    public Gold(int amount) {
        super(null);
        this.amount = amount;

    }
    private int generateAmountOfGoldByLevel(int level){
        Random r=new Random();

        if(level<10){
            return r.nextInt(level+1)+1;
        }else if(level<50){
            return r.nextInt(level*2)+level;
        }else {
            return r.nextInt(level*4)+level*2;
        }



    }

    @Override
    public String getDescription() {
        return "Gold";
    }

    @Override
    public String getName() {
        return "Gold x "+amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Item item) {
        return (item instanceof Gold);

    }

    @Override
    public void merge(Stackable item) {
        this.amount+=item.getAmount();

    }

    @Override
    public boolean use(GameCharacter character, int amount) {
        if(this.amount<amount){
            return false;
        }else{
            this.amount-=amount;
            return true;
        }
    }

    @Override
    public void setAmount(int amount) {
        this.amount=amount;
    }

    @Override
    public Item getOneItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
