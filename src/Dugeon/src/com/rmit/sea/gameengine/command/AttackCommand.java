package com.rmit.sea.gameengine.command;

import com.rmit.sea.gameengine.behaviour.skill.AttackBehaviour;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.UsableMP;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.view.ConsoleView;
import java.util.Iterator;
import java.util.List;

public class AttackCommand implements Command {

    private AttackBehaviour attackBehaviour;
    private boolean executed;

    public AttackBehaviour getAttackBehaviour() {
        return attackBehaviour;
    }

    public AttackCommand(AttackBehaviour attackBehaviour) {
        this.attackBehaviour = attackBehaviour;
        this.executed = false;
    }

    /**
     * execute command
     */
    @Override
    public void execute() {
        //set remaining MP of character if it is player
        GameCharacter character = attackBehaviour.getAttacker();
        if (character.isSkillUsable(attackBehaviour.getSkill())) {
            attackBehaviour.inflictDamage();
        }
        
        executed = true;
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public String toString() {
        String desc = attackBehaviour.getAttacker().getName() + " attacked ";
        List<Vulnerable> targets = attackBehaviour.getTargetsToAttack();

        // Get targets name
        Iterator i = targets.iterator();
        Coordinate attackerCoor = attackBehaviour.getAttacker().getCoordinate();
        Vulnerable target = null;
        GameCharacter gameCharacter = null;

        while (i.hasNext()) {
            if (target != null) {
                desc += ", ";
            }

            target = (Vulnerable) i.next();
            if (target instanceof GameCharacter) {
                gameCharacter = (GameCharacter) target;

                // Check if this is the attacker or not
                if (!(gameCharacter.getCoordinate().equals(attackerCoor))) {
                    // not the attacker, add its name
                    desc += gameCharacter.getName();
                } else {
                    // it is the attacker, don't add name
                    target = null;
                }
            }
        }

        return desc;
    }
}
