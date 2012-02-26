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
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class CatastropheSkill extends Skill implements AttackableSkill, NotRequireTarget, GeneralClass {
    
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
                return 5;
            case 1:
                return 10;

            case 2:
                return 15;

            case 3:
                return 30;

            case 4:
                return 45;

            case 5:
                return 70;
            default:
                return 70;


        }
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 5);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 50);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 120);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 200);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 200);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets=new ArrayList<Vulnerable>();
        for(GameCharacter c:gameCharacters){
            if(c instanceof Vulnerable&& !(c instanceof Player)){
                targets.add((Vulnerable)c);
            }
        }
        return targets;
    }

    @Override
    public String getSkillDescription() {

        return "Hit all enemy(this is likely a test skill";
    }


    @Override
    public String getSkillName() {
        return "Catastrophe";
    }

}
