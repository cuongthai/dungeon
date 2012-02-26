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
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.DoubleDamage;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class UnsheathSkill extends Skill implements AttackableSkill, RequireMeleeTarget, DoubleDamage,SwordClass {

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
        int damage = 0;
        switch (level) {
            case 0:
                damage = 20;
                break;
            case 1:
                damage = 40;
                break;
            case 2:
                damage = 60;
                break;
            case 3:
                damage = 80;
                break;
            case 4:
                damage = 100;
                break;
            case 5:
                damage = 140;
                break;
            default:
                damage = 140;

        }
        return new Damage(Constant.PHYSICAL_ELEMENT, damage);
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
                return 12;
            case 4:
                return 14;
            case 5:
                return 16;
            default:
                return 16;
        }
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 400;
            case 2:
                return 800;
            case 3:
                return 1600;
            case 4:
                return 3200;
            case 5:
                return 5000;
            default:
                return 5000;
        }
    }

    @Override
    public int getDoubleDamagePercentage(int level) {
        switch (level) {
            case 0:
                return 20;
            case 1:
                return 25;
            case 2:
                return 30;
            case 3:
                return 40;
            case 4:
                return 50;
            case 5:
                return 70;
            default:
                return 70;
        }
    }

    @Override
    public String getSkillDescription() {


        return "Sword style attack, can only use with sword. A quick drawing of the sword causing damage to the enemy right in front of player and add a chance to double damage";


    }

    @Override
    public String getSkillName() {
        return "Unsheath";
    }
}
