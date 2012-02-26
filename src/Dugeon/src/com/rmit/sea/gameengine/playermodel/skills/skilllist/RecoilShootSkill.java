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
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.ArrowClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.Knockable;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author gia
 */
public class RecoilShootSkill extends Skill implements RangeAttack, Knockable, SpecialEffect, AttackableSkill,ArrowClass{

    @Override
    public String getSkillDescription() {
        return "A shoot that will knock back an enemy";
    }

    @Override
    public String getSkillName() {
        return "Recoil Shoot";
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
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 2;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 15;
            case 5:
                return 20;
            default:
                return 20;
        }
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
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 15);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 30);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 50);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 70);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 70);
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
    public int getAmountOfArrowUse() {
        return 1;
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Coordinate userCo = ((GameCharacter) user).getCoordinate();
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
                Vulnerable target=((Vulnerable) c);

                Random r = new Random();
                if (r.nextInt(100) <= getKnockBackPercentage(level)) {
                    Coordinate targetCo = ((GameCharacter) target).getCoordinate();
                    int signumX = (int) Math.signum(targetCo.getX() - userCo.getX());
                    int signumY = (int) Math.signum(targetCo.getY() - userCo.getY());
                    Coordinate newCoordinate = new Coordinate(targetCo.getX() + signumX, targetCo.getY() + signumY);

                    if (fullMap.get(newCoordinate) instanceof Walkable) {
                        ((GameCharacter) target).setCoordinate(newCoordinate);

                    }
                }
            }
        }
    }

    @Override
    public int getKnockBackPercentage(int level) {
        switch (level) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 20;
            case 3:
                return 30;
            case 4:
                return 40;
            case 5:
                return 50;
            default:
                return 50;
        }
    }

}

