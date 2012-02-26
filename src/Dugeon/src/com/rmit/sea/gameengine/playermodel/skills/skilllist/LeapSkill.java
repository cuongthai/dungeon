/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.List;
import java.util.Map;

/**
 *
 * @author s3269900 Dang Nguyen Hoang Gia
 */
public class LeapSkill extends Skill implements SpecialEffect, RequireMeleeTarget, GeneralClass {

    @Override
    public String getSkillDescription() {
        return "Athletics skill. Leap over an enemy to get out of a hard time";
    }

    @Override
    public String getSkillName() {
        return "Leap";
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
                return 100;
            case 1:
                return 90;
            case 2:
                return 80;
            case 3:
                return 75;
            case 4:
                return 65;
            case 5:
                return 50;
            default:
                return 50;
        }
    }



    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
                Vulnerable target=((Vulnerable) c);
                Coordinate userCo = ((GameCharacter) user).getCoordinate();
                Coordinate targetCo = ((GameCharacter) target).getCoordinate();
                int signumX = (int) Math.signum(targetCo.getX() - userCo.getX());
                int signumY = (int) Math.signum(targetCo.getY() - userCo.getY());

                Coordinate newCoordinate = new Coordinate(targetCo.getX() + signumX * 1, targetCo.getY() + signumY * 1);
                if (fullMap.get(newCoordinate) instanceof Walkable) {
                    ((GameCharacter) user).setCoordinate(newCoordinate);
                }

            }
        }
    }
}
