/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Walkable;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gia
 */
public class DashSkill extends Skill implements RangeAttack, SpecialEffect, GeneralClass {

    @Override
    public String getSkillDescription() {
        return "Dashing forward to fight (or to flee).";
    }

    @Override
    public String getSkillName() {
        return "Dash";
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
    public int range(int level) {
        switch (level) {
            case 0:
                return 2;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 3;
            case 4:
                return 3;
            case 5:
                return 4;
            default:
                return 4;
        }
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Player player = (Player) user;
        int signumX = 0;
        int signumY = 0;
        switch (direction) {
            case FORWARD:
                signumY = -1;
                break;
            case BACKWARD:
                signumY = 1;
                break;
            case LEFT:
                signumX = -1;
                break;
            case RIGHT:
                signumX = 1;
                break;
        }

        int i = 1;
        Coordinate newCoordinate;
        do {
            newCoordinate = new Coordinate(player.getCoordinate().getX() + signumX * i, player.getCoordinate().getY() + signumY * i);
            i++;
        } while (fullMap.get(newCoordinate) instanceof Walkable && i <= range(level) + 1);
        newCoordinate = new Coordinate(player.getCoordinate().getX() + signumX * (i-2), player.getCoordinate().getY() + signumY * (i-2));
        player.setCoordinate(newCoordinate);
    }
}
