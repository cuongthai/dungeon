package com.rmit.sea.gameengine.command;

import com.rmit.sea.gameengine.behaviour.GetItemBehaviour;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.List;

public class GetItemCommand implements Command {

    private GameCharacter character;
    private GetItemBehaviour getItemBehaviour;
    private boolean executed;
    private String desc;

    public GetItemCommand(GameCharacter character, GetItemBehaviour getItemBehaviour) {
        this.character = character;
        this.getItemBehaviour = getItemBehaviour;
        this.executed = false;
    }

    @Override
    public void execute() {
        Item itemWillBePicked = getItemBehaviour.getPickUpItem();

        if (itemWillBePicked != null) {
            character.getItem(itemWillBePicked);
            getItemBehaviour.getItems().remove(itemWillBePicked);
            // Generate description
            desc = character.getName() + " picked up item " + itemWillBePicked.getName();
        }

        executed = true;
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public String toString() {
        return desc;
    }
}
