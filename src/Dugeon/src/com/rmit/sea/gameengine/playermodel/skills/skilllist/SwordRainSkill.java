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
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class SwordRainSkill extends Skill implements AttackableSkill, NotRequireTarget,SwordClass {


    @Override
    public Damage getDamage(int level) {
         switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 40);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 60);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 90);
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
        Coordinate userCo = ((GameCharacter) user).getCoordinate();
        Coordinate c1 = new Coordinate(userCo.getX() + 1, userCo.getY());
            Coordinate c2 = new Coordinate(userCo.getX() - 1, userCo.getY());
            Coordinate c3 = new Coordinate(userCo.getX(), userCo.getY() + 1);
            Coordinate c4 = new Coordinate(userCo.getX(), userCo.getY() - 1);
            possibleTargets.add(c1);
            possibleTargets.add(c2);
            possibleTargets.add(c3);
            possibleTargets.add(c4);
        if (level > 2) {
            Coordinate c5 = new Coordinate(userCo.getX() + 1, userCo.getY()+1);
            Coordinate c6 = new Coordinate(userCo.getX() - 1, userCo.getY()-1);
            Coordinate c7 = new Coordinate(userCo.getX()-1, userCo.getY() + 1);
            Coordinate c8 = new Coordinate(userCo.getX()+1, userCo.getY() - 1);
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
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 500;
            case 2:
                return 1000;
            case 3:
                return 2000;
            case 4:
                return 4000;
            case 5:
                return 7000;

            default:
                return 7000;

        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 10;
            case 1:
                return 15;

            case 2:
                return 20;

            case 3:
                return 25;

            case 4:
                return 40;

            case 5:
                return 65;
            default:
                return 65;


        }
    }

    @Override
    public String getSkillDescription() {

       return "Sword style attack, can only use with sword. Calling thousand of swords to rain down from the sky(sorry the ceiling) to strike the enemies";

    }

    @Override
    public String getSkillName() {
        return "Sword Rain";
    }
}
