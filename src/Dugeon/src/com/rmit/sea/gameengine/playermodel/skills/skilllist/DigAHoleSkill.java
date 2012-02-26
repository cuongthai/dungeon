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
import java.util.Random;

/**
 *
 * @author Magickey
 */
public class DigAHoleSkill extends Skill implements SpecialEffect, NotRequireTarget, GeneralClass {

    @Override
    public String getSkillDescription() {
        return "Weird skill. Use mainly for escape. Will put you into a level lower than the current level. Need at least 200MP to use and will consume all of your current MP. There is also a 10% chance that you will go to a level*5 the current level";
    }

    @Override
    public String getSkillName() {
        return "Dig A Hole";
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
        return 200;
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Player p=(Player)user;
        Random r=new Random();
        if(r.nextInt(100)<10-level){
            p.setLevel(p.getLevel()*5);
        }else{
            p.setLevel(r.nextInt(p.getLevel()));
        }
        p.getCharacterDetailInfo().increaseMp(-p.getCharacterDetailInfo().getMp());
    }

}
