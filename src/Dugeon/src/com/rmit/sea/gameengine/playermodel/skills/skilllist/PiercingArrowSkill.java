/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Vulnerable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.ArrowClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RangeAttack;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magickey
 */
public class PiercingArrowSkill extends Skill implements RangeAttack, AttackableSkill,ArrowClass{

    @Override
    public String getSkillDescription() {
        return "A piercing shoot that will go through a line of enemies";
    }

    @Override
    public String getSkillName() {
        return "Piercing Arrow";
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        switch (level) {
            case 0:
                return 0;
            case 1:
                return 300;
            case 2:
                return 700;
            case 3:
                return 1200;
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
                return 2;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 15;
            case 5:
                return 20;
            default:
                return 20;
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
                return 4;
            case 4:
                return 5;
            case 5:
                return 5;
            default:
                return 5;
        }
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 10);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 15);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 30);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 60);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 100);
            case 5:
                return new Damage(Constant.PHYSICAL_ELEMENT, 150);
            default:
                return new Damage(Constant.PHYSICAL_ELEMENT, 150);
        }
    }

    @Override
    public List<Vulnerable> getTargetsToAttack(List<GameCharacter> gameCharacters, Attackable user, Coordinate executeCoordinate, int level) {
        Player p = (Player) user;
        List<Vulnerable> targets = new ArrayList<Vulnerable>();
        Coordinate playerCo = p.getCoordinate();
        int signumX = (int) Math.signum(executeCoordinate.getX() - p.getCoordinate().getX());
        int signumY = (int) Math.signum(executeCoordinate.getY() - p.getCoordinate().getY());
        for(int i=1;i<=range(level);i++){
            Coordinate c=new Coordinate(playerCo.getX()+i*signumX,playerCo.getY()+i*signumY);
            for(GameCharacter character:gameCharacters){
                if(character.getCoordinate().equals(c)){
                    targets.add((Vulnerable)character);
                }
            }


        }
        return targets;
    }

    @Override
    public int getAmountOfArrowUse() {
        return 1;
    }

}
