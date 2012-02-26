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
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author s3269900 Dang Nguyen Hoang Gia
 */
public class ReverseDestinySkill extends Skill implements SpecialEffect, RequireMeleeTarget, GeneralClass {

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
                return 80;
            case 1:
                return 75;
            case 2:
                return 70;
            case 3:
                return 65;
            case 4:
                return 60;
            case 5:
                return 55;
            default:
                return 55;
        }
    }

    @Override
    public String getSkillDescription() {

        return "Magic attack, switch place with your target. Very helpful to escape surrounding enemies";


    }

    @Override
    public String getSkillName() {

        return "Reverse Destiny";
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        Coordinate userCo = ((GameCharacter) user).getCoordinate();
        for (GameCharacter c : gameCharacters) {
            if (c.getCoordinate().equals(executeCoordinate) && c instanceof Vulnerable) {
                Vulnerable target = (Vulnerable) c;
                Coordinate targetCo = ((GameCharacter) target).getCoordinate();
                Coordinate temp = new Coordinate(targetCo.getX(), targetCo.getY());

                ((GameCharacter) target).setCoordinate(userCo.clone());
                ((GameCharacter) user).setCoordinate(temp);

            }
        }
    }
}
