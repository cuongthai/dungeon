package com.rmit.sea.gameengine.command;

import com.rmit.sea.gameengine.behaviour.UseItemBehaviour;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.iteminterface.Usable;
import com.rmit.sea.gameengine.iteminterface.Healable;
import com.rmit.sea.gameengine.iteminterface.Learnable;
import com.rmit.sea.gameengine.iteminterface.PowerUpHp;
import com.rmit.sea.gameengine.iteminterface.PowerUpMp;
import com.rmit.sea.gameengine.iteminterface.Recoverable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.iteminterface.Equipable;
import com.rmit.sea.view.ConsoleView;

public class UseItemCommand implements Command {

    private GameCharacter user;
    private UseItemBehaviour useItemBehaviour;
    private boolean execute;

    public UseItemCommand(GameCharacter user, UseItemBehaviour useItemBehaviour) {
        this.user = user;
        this.useItemBehaviour = useItemBehaviour;
        execute = false;
    }

    /**
     * defend on the type of item to equip, use, or learn
     */
    @Override
    public void execute() {
        Item item = useItemBehaviour.getItem();
        if (item instanceof Usable) {
            ((Usable) item).use(user, useItemBehaviour.getAmount());
        } else if (item instanceof Learnable) {
            if (user instanceof Player) {
                Player p = (Player) user;
                p.getSkillManager().addSkill((((Learnable) item).getSkillToLearn()));
            }
        } else if (item instanceof Equipable) {
            if (user instanceof Player) {
                Player p = (Player) user;
                p.equipItem(item);
            }
        }
        user.getInventory().removeItem(item);

        execute = true;
    }

    @Override
    public boolean isExecuted() {
        return execute;
    }

    @Override
    public String toString() {
        return user.getName() + " used item " + useItemBehaviour.getItem().getName();
    }
}
