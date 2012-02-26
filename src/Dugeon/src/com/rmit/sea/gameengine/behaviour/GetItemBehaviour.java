package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.List;

public class GetItemBehaviour {
    private GameCharacter player;
    private List<Item> items;

    public GetItemBehaviour(GameCharacter player, List<Item> items) {
        this.player=player;
        this.items=items;
    }

    public List<Item> getItems() {
        return items;
    }

    public GameCharacter getPlayer() {
        return player;
    }
    
    public Item getPickUpItem(){
        Item itemWillBePicked = null;
        Coordinate characterCoordinate = player.getCoordinate();

        for(int i=items.size()-1;i>=0;i--){
            if (characterCoordinate.equals(items.get(i).getCoordinate())) {
                itemWillBePicked = items.get(i);
                break;
            }
        }
        
        return itemWillBePicked;
    }
}
