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
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SwordClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanggia
 */
public class SwordAttackSkill extends Skill implements AttackableSkill,RequireMeleeTarget,SwordClass {

    



    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters,Attackable user,Coordinate executeCoordinate, int level) {
        List<Vulnerable> targets=new ArrayList<Vulnerable>();
        for(GameCharacter c:gameCharacters){
            if(c.getCoordinate().equals(executeCoordinate)&&c instanceof Vulnerable){
                targets.add((Vulnerable)c);
            }
        }
        return targets;
    }

    @Override
    public Damage getDamage(int level) {
       switch(level){
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT,10);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT,20);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT,40);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT,60);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT,80);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT,90);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT,90);
        }
    }

    @Override
    public int getMpCost(int level) {
        switch(level){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 1;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            default:
                return 3;
        }
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch(level){
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

        return "Sword style attack, can only be used using sword";


    }

    @Override
    public String getSkillName() {
        return "Sword Attack";
    }
}
