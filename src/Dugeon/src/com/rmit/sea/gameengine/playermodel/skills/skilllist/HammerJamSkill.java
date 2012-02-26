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
public class HammerJamSkill extends Skill implements AttackableSkill, Knockable, SpecialEffect, RequireMeleeTarget, HammerClass {

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 30);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 60);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 100);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
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
    public String getSkillDescription() {

        return "Hammer style attack, use with hammer. Jam the hammer into the face of your enemy. May knock them back 1 distance unit";

    }

    @Override
    public String getSkillName() {
        return "Hammer Jam";
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
}
