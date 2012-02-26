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
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class EarthquakeSkill extends Skill implements AttackableSkill, NotRequireTarget,HammerClass {

    

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
                return new Damage(Constant.PHYSICAL_ELEMENT, 40);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        int distance=1;
        if(level==3){
            distance=2;
        }else if(level>=4){
            distance=3;
        }
        for (GameCharacter c : gameCharacters) {
                if (c.getCoordinate().distanceTo(executeCoordinate)<=distance && !(c instanceof Player)&& c instanceof Vulnerable) {
                    targets.add((Vulnerable) c);
                }
        }

        return targets;
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
                return 900;
            case 4:
                return 1500;
            case 5:
                return 3000;

            default:
                return 3000;

        }
    }

    @Override
    public int getMpCost(int level) {
        switch (level) {
            case 0:
                return 10;
            case 1:
                return 20;

            case 2:
                return 40;

            case 3:
                return 60;

            case 4:
                return 80;

            case 5:
                return 100;
            default:
                return 100;


        }
    }

    @Override
    public String getSkillDescription() {

        return "Hammer style attack, use with hammer. Jam the hammer to the ground causing earthquake. Hit all enemies in a certain distance unit radius";
    }

    @Override
    public String getSkillName() {
        return "Earthquake";
    }
}
