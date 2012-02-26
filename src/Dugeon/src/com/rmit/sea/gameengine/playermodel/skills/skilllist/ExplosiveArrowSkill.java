/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.ArrowClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gia
 */
public class ExplosiveArrowSkill extends Skill implements RangeAttack, AttackableSkill,ArrowClass {

    @Override
    public String getSkillDescription() {
        return "Shoot an explosive arrow to the enemy. Need bow and arrow. Deal damage to multiple enemies around the target";
    }

    @Override
    public String getSkillName() {
        return "Explosive Arrow";
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
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 20;
            case 1:
                return 30;
            case 2:
                return 40;
            case 3:
                return 50;
            case 4:
                return 60;
            case 5:
                return 80;
            default:
                return 80;
        }
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 5);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 45);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 70);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 100);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 100);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        List<Coordinate> possibleTargets = new ArrayList<Coordinate>();
        possibleTargets.add(executeCoordinate);

        Coordinate c1 = new Coordinate(executeCoordinate.getX() + 1, executeCoordinate.getY());
        Coordinate c2 = new Coordinate(executeCoordinate.getX() - 1, executeCoordinate.getY());
        Coordinate c3 = new Coordinate(executeCoordinate.getX(), executeCoordinate.getY() + 1);
        Coordinate c4 = new Coordinate(executeCoordinate.getX(), executeCoordinate.getY() - 1);
        possibleTargets.add(c1);
        possibleTargets.add(c2);
        possibleTargets.add(c3);
        possibleTargets.add(c4);
        if (level > 2) {
            Coordinate c5 = new Coordinate(executeCoordinate.getX() + 1, executeCoordinate.getY() + 1);
            Coordinate c6 = new Coordinate(executeCoordinate.getX() - 1, executeCoordinate.getY() - 1);
            Coordinate c7 = new Coordinate(executeCoordinate.getX() - 1, executeCoordinate.getY() + 1);
            Coordinate c8 = new Coordinate(executeCoordinate.getX() + 1, executeCoordinate.getY() - 1);
            possibleTargets.add(c5);
            possibleTargets.add(c6);
            possibleTargets.add(c7);
            possibleTargets.add(c8);
        }

        for (GameCharacter c : gameCharacters) {
            for (Coordinate co : possibleTargets) {
                if (c.getCoordinate().equals(co) && c instanceof Vulnerable) {
                    targets.add((Vulnerable) c);
                }
            }
        }

        return targets;
    }

    @Override
    public int range(int level) {
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
                return 5;
            default:
                return 5;
        }

    }

    @Override
    public int getAmountOfArrowUse() {
        return 1;
    }
}
