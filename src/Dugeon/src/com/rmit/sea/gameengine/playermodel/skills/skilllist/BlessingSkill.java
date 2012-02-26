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
 * @author gia
 */
public class BlessingSkill extends Skill implements SpecialEffect,GeneralClass,NotRequireTarget {

    @Override
    public String getSkillDescription() {
        return "Blessing. Use to heal a percentage of HP";
    }

    @Override
    public String getSkillName() {
        return "Blessing";
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 300;
            case 2:
                return 800;
            case 3:
                return 1800;
            case 4:
                return 3000;
            case 5:
                return 4500;
            default:
                return 4500;
        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 60;
            case 1:
                return 100;
            case 2:
                return 150;
            case 3:
                return 200;
            case 4:
                return 250;
            case 5:
                return 250;
            default:
                return 250;
        }
    }

    private int percentageOfHpHeal(int level){
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
                return 30;
            case 5:
                return 35;
            default:
                return 35;
        }
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Player p=(Player)user;
        int maxHp=p.getCharacterDetailInfo().getMaxHp();
        p.getCharacterDetailInfo().increaseHp(maxHp*percentageOfHpHeal(level)/100);
    }

}

