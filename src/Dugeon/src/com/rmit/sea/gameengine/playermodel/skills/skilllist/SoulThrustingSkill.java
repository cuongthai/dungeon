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
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.CostHp;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.InstanceKill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hoanggia
 */
public class SoulThrustingSkill extends Skill implements AttackableSkill, RequireMeleeTarget, InstanceKill, CostHp ,SwordClass{

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
        Random r = new Random();
        if (r.nextInt(100) <= getInstanceKillPercentage(level)) {
            return new Damage(Constant.PHYSICAL_ELEMENT, Integer.MAX_VALUE);
        } else {
            switch (level) {
                case 0:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 50);
                case 1:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 70);
                case 2:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 100);
                case 3:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 130);
                case 4:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 160);
                case 5:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 200);
                default:
                    return new Damage(Constant.PHYSICAL_ELEMENT, 200);
            }
        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 5;
            case 1:
                return 8;
            case 2:
                return 10;
            case 3:
                return 20;
            case 4:
                return 30;
            case 5:
                return 40;
            default:
                return 40;
        }
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 1000;
            case 2:
                return 2000;
            case 3:
                return 4000;
            case 4:
                return 8000;
            case 5:
                return 16000;
            default:
                return 16000;
        }
    }

    @Override
    public int getInstanceKillPercentage(int level) {
        switch (level) {
            case 0:
                return 5;
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 10;
            default:
                return 10;
        }
    }

    @Override
    public int getHpCost(int level) {
        switch (level) {
            case 0:
                return 10;
            case 1:
                return 15;
            case 2:
                return 20;
            case 3:
                return 30;
            case 4:
                return 50;
            case 5:
                return 80;
            default:
                return 80;
        }
    }

    @Override
    public String getSkillDescription() {


        return "Sword style ultimate attack, can only use with sword. Thrusting the sword to the very soul of the enemy. Cause massive damage and a chance to instance kill but with the cost of the player's own soul. However, the instance kill will not work with boss.";


    }

    @Override
    public String getSkillName() {
        return "Soul Thrusting";
    }
}
