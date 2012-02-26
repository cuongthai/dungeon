package com.rmit.sea.gameengine.item;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.Healable;
import com.rmit.sea.gameengine.iteminterface.Stackable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;

public class HpPotion extends Item implements Usable, Healable,Stackable,Valuable {

    private int hp;
    private int amount;
    private int value;

    public HpPotion(Coordinate coordinate, int level,int amount) {
        super(coordinate);
        hp = (level / 10 + 1) * 20;
        value=(level/10+1)*10;
        this.amount=amount;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public String getDescription() {
        return "Heal " + getHp() + " HP";
    }

    @Override
    public String getName() {
        return "HP Potion x "+amount ;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Item item) {
        if(item instanceof HpPotion){
            if(((HpPotion)item).getHp()==hp){
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
    public boolean use(GameCharacter character, int amount) {
        if(this.amount-amount>=0){
            character.getCharacterDetailInfo().increaseHp(hp*amount);
            this.amount-=amount;
            return true;
        }else{
            return false;
        }
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
        return new HpPotion(null, hp, 1);
    }

}
