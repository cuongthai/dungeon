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
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Magickey
 */
public class CureSkill extends Skill implements SpecialEffect,GeneralClass,NotRequireTarget {

    @Override
    public String getSkillDescription() {
        return "Cure. Use to heal";
    }

    @Override
    public String getSkillName() {
        return "Cure";
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
                return 30;
            case 1:
                return 50;
            case 2:
                return 70;
            case 3:
                return 90;
            case 4:
                return 110;
            case 5:
                return 150;
            default:
                return 150;
        }
    }

    private int amountOfHpHeal(int level){
        switch (level) {
            case 0:
                return 40;
            case 1:
                return 80;
            case 2:
                return 120;
            case 3:
                return 200;
            case 4:
                return 250;
            case 5:
                return 300;
            default:
                return 300;
        }
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Player p=(Player)user;
        p.getCharacterDetailInfo().increaseHp(amountOfHpHeal(level));
    }

}
