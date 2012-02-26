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
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.Knockable;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author hoanggia
 */
public class HammerBattingSkill extends Skill implements AttackableSkill, Knockable, RequireMeleeTarget, SpecialEffect, HammerClass {

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
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 150);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 150);
        }
    }

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
    public int getKnockBackPercentage(int level) {
        switch (level) {
            case 0:
                return 30;
            case 1:
                return 40;
            case 2:
                return 50;
            case 3:
                return 60;
            case 4:
                return 70;
            case 5:
                return 80;
            default:
                return 80;
        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 4;
            case 1:
                return 8;
            case 2:
                return 10;
            case 3:
                return 15;
            case 4:
                return 20;
            case 5:
                return 25;
            default:
                return 25;
        }
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 300;
            case 2:
                return 700;
            case 3:
                return 1200;
            case 4:
                return 2000;
            case 5:
                return 3500;
            default:
                return 3500;
        }
    }

    @Override
    public String getSkillDescription() {

        return "Hammer style attack, use with hammer. It's baseball time! Hit the enemy, deal damage and add a chance to knock the enemy back as far as it can. If the enemy meets a Wall during the fly, double the damage. If the enemy meets another enemy, that enemy also taking damage";


    }

    @Override
    public String getSkillName() {
        return "Hammer Batting";
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Coordinate userCo = ((GameCharacter) user).getCoordinate();
        GameCharacter target = null;
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
            }
        }
        if (target != null) {
            Random r = new Random();
            if (r.nextInt(100) <= getKnockBackPercentage(level)) {
                Coordinate targetCo = target.getCoordinate();
                int signumX = (int) Math.signum(targetCo.getX() - userCo.getX());
                int signumY = (int) Math.signum(targetCo.getY() - userCo.getY());
                int i = 1;
                Coordinate newCoordinate;
                do {
                    newCoordinate = new Coordinate(targetCo.getX() + signumX * i, targetCo.getY() + signumY * i);
                    i++;
                } while (fullMap.get(newCoordinate) instanceof Walkable);
                Coordinate newTargetCoordinate = new Coordinate(targetCo.getX() + signumX * (i - 2), targetCo.getY() + signumY * (i - 2));
                ((GameCharacter) target).setCoordinate(newTargetCoordinate);

                if (fullMap.get(newCoordinate) instanceof Vulnerable) {
                    ((Vulnerable) (fullMap.get(newCoordinate))).receiveDamage(getDamage(level));
                } else {
                    ((Vulnerable)target).receiveDamage(getDamage(level));
                }
            }
        }

    }
}
