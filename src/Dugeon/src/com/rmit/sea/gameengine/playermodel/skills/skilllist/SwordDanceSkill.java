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
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class SwordDanceSkill extends Skill implements AttackableSkill, RequireMeleeTarget,SwordClass {

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
                return new Damage(Constant.PHYSICAL_ELEMENT, 40);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 60);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        List<Coordinate> possibleTargets = new ArrayList<Coordinate>();
        Coordinate userCo = ((GameCharacter) user).getCoordinate();
        if (level <= 2) {
            Coordinate c1;
            Coordinate c2;
            if (executeCoordinate.getX() == userCo.getX()) {
                c1 = new Coordinate(userCo.getX() + 1, userCo.getY());
                c2 = new Coordinate(userCo.getX() - 1, userCo.getY());
            } else {
                c1 = new Coordinate(userCo.getX(), userCo.getY() + 1);
                c2 = new Coordinate(userCo.getX(), userCo.getY() - 1);
            }
            possibleTargets.add(c1);
            possibleTargets.add(c2);
            possibleTargets.add(executeCoordinate);


        } else {
            Coordinate c1 = new Coordinate(userCo.getX() + 1, userCo.getY());
            Coordinate c2 = new Coordinate(userCo.getX() - 1, userCo.getY());
            Coordinate c3 = new Coordinate(userCo.getX(), userCo.getY() + 1);
            Coordinate c4 = new Coordinate(userCo.getX(), userCo.getY() - 1);
            possibleTargets.add(c1);
            possibleTargets.add(c2);
            possibleTargets.add(c3);
            possibleTargets.add(c4);
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
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 300;
            case 2:
                return 600;
            case 3:
                return 1000;
            case 4:
                return 2000;
            case 5:
                return 3500;

            default:
                return 3500;

        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 5;
            case 1:
                return 10;

            case 2:
                return 15;

            case 3:
                return 20;

            case 4:
                return 25;

            case 5:
                return 30;
            default:
                return 30;


        }
    }

    @Override
    public String getSkillDescription() {

        return "Sword style attack, can only be used using sword. When attack can hit all 4 enemies (if existed) in all four directions ";

    }

    @Override
    public String getSkillName() {
        return "Sword Dance";
    }
}
