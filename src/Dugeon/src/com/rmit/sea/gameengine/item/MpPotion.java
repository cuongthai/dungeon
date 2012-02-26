package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.Recoverable;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

public class MpPotion extends Item implements Usable, Recoverable, Stackable,Valuable {
    private  int mp;
    private int amount;
    private int value;
    public MpPotion(Coordinate coordinate,int level,int amount) {
        super(coordinate);
        mp=(level/10+1)*20;
        value=(level/10+1)*10;
        this.amount=amount;
    }



    @Override
    public int getMp() {
        return mp;
    }


    @Override
    public String getDescription() {
        return "Recover "+getMp()+" MP";
    }

    @Override
    public String getName() {
        return "MP Potion x "+amount;
    }

    @Override
    public boolean use(GameCharacter character, int amount) {
        if(this.amount-amount>=0){
            character.getCharacterDetailInfo().increaseMp(mp*amount);
            this.amount-=amount;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Item item) {
        if(item instanceof MpPotion){
            if(((MpPotion)item).getMp()==mp){
                return true;
            }
        }
        return false;
    }

    @Override
    public void merge(Stackable item) {
        this.amount+=item.getAmount();
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
        return new MpPotion(null, mp, 1);
    }


}
