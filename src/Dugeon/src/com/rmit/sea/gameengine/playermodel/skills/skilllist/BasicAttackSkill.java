/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.view.ConsoleView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class BasicAttackSkill extends Skill implements AttackableSkill,RequireMeleeTarget,GeneralClass {

    

    @Override
    public int getMpCost(int level) {
        return 0;
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 1);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 5);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 15);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 25);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 25);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters,Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
                targets.add((Vulnerable) c);
            }
        }
        return targets;
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 150;
            case 2:
                return 300;
            case 3:
                return 600;
            case 4:
                return 1200;
            case 5:
                return 2000;
            default:
                return 2000;
        }
    }

    @Override
    public String getSkillDescription() {
        return "Just a basic attack, can be used with any weapon";
    }

    @Override
    public String getSkillName() {
        return "Basic Attack";
    }
}
