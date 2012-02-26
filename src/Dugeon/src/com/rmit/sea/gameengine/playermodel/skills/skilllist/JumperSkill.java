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
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author gia
 */
public class JumperSkill extends Skill implements SpecialEffect, NotRequireTarget, GeneralClass {

    @Override
    public String getSkillDescription() {
        return "I am jumper";
    }

    @Override
    public String getSkillName() {
        return "Jumper";
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
                return 200;
            case 1:
                return 180;
            case 2:
                return 150;
            case 3:
                return 130;
            case 4:
                return 120;
            case 5:
                return 100;
            default:
                return 100;
        }
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Random r=new Random();
        Coordinate destination=null;
        do{
            int time=r.nextInt(fullMap.size());
            int i=0;
            Iterator ite=fullMap.keySet().iterator();
            while(i!=time){
                destination=(Coordinate)ite.next();
                i++;
            }
            
        }while(!(fullMap.get(destination) instanceof Walkable));
        ((Player)user).setCoordinate(destination);
    }

}

