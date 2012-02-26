/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class HammerAttackSkill extends Skill implements AttackableSkill, RequireMeleeTarget, HammerClass {

    

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
                targets.add((Vulnerable) c);
            }
        }
        return targets;
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 25);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 35);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 55);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 75);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 105);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 105);
        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 2;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 7;
            default:
                return 7;
        }
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 200;
            case 2:
                return 400;
            case 3:
                return 800;
            case 4:
                return 1600;
            case 5:
                return 3000;
            default:
                return 3000;
        }
    }

    @Override
    public String getSkillDescription() {

        return "Hammer style attack, can only be used with hammer";


    }

    @Override
    public String getSkillName() {
        return "Hammer Attack";
    }
}
