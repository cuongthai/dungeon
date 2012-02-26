/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.behaviour.skill;

import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.DoubleDamage;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *Melee (not range) attack will have their necessary information store here
 * @author thailycuong1202
 */
public class SimpleAttackBehaviour extends AttackBehaviour {

    public SimpleAttackBehaviour(GameCharacter attacker, Map<Coordinate, ViewablePixel> fullMap, List<GameCharacter> allCharacters, Coordinate executeCoordinate) {
        super(attacker, fullMap, allCharacters, executeCoordinate);
    }

    /**
     * this will get targets for the attack by calling the method in skill
     * @return List<Vulnerable> of the targets
     */
    @Override
    public List<Vulnerable> getTargetsToAttack() {
        List<Vulnerable> targets=new ArrayList<Vulnerable>();
        //ConsoleView.out(getPlayer().getEquipSkill());
        if (getSkill() instanceof AttackableSkill) {
            targets = ((AttackableSkill) getSkill()).getTargetsToAttack(getAllCharacters(),
                    (Attackable) getAttacker(),
                    getExecuteCoordinate(),
                    getSkill().getCurrentLevel());
        }
        return targets;
    }

    /**
     * get the damage. Dealing with double damage here
     * @return
     */
    @Override
    public Damage getDamage() {

        Damage skillDamage = ((AttackableSkill) getSkill()).getDamage(getAttacker().getEquipSkill().getCurrentLevel());
        Damage playerDamage = ((Attackable) getAttacker()).getDamage();
        if (getSkill() instanceof DoubleDamage) {
            Random r = new Random();
            if (r.nextInt(100) <= ((DoubleDamage) getSkill()).getDoubleDamagePercentage(getSkill().getCurrentLevel())) {
                skillDamage.setDamage(skillDamage.getDamage() * 2);
            }
        }
        return playerDamage.mergeDamage(skillDamage);
    }

    /**
     * execute special effect here
     */
    @Override
    public void specialEffect() {
        if (getSkill() instanceof SpecialEffect) {
            ((SpecialEffect) getSkill()).executeSpecialEffect((Attackable)getAttacker(),
                    getSkill().getCurrentLevel(),
                    getExecuteCoordinate(),
                    getAllCharacters(),
                    getFullMap(), null);
        }
    }


}
