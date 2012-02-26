package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.item.Item;

public class UseItemBehaviour {
    private Item item;
    private int amount;

    public UseItemBehaviour(Item item,int amount) {
        this.item = item;
        this.amount=amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }


}
