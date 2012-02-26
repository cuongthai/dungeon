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
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.AttackableSkill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.HammerClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.RequireMeleeTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gia
 */
public class ShockwaveSkill extends Skill implements AttackableSkill, RequireMeleeTarget, HammerClass {

    @Override
    public String getSkillDescription() {
        return "A hit of hammer sending shockwave forward the enemies.";
    }

    @Override
    public String getSkillName() {
        return "Shockwave";
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
                return 4;
            case 1:
                return 8;
            case 2:
                return 10;
            case 3:
                return 15;
            case 4:
                return 20;
            case 5:
                return 25;
            default:
                return 25;
        }
    }

    @Override
    public Damage getDamage(int level) {
        switch (level) {
            case 0:
                return new Damage(Constant.PHYSICAL_ELEMENT, 20);
            case 1:
                return new Damage(Constant.PHYSICAL_ELEMENT, 40);
            case 2:
                return new Damage(Constant.PHYSICAL_ELEMENT, 60);
            case 3:
                return new Damage(Constant.PHYSICAL_ELEMENT, 80);
            case 4:
                return new Damage(Constant.PHYSICAL_ELEMENT, 110);
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

        int range = 0;
        if (level < 2) {
            range = 3;
        } else if (level < 5) {
            range = 4;
        } else {
            range = 5;
        }


        List<ViewablePixel> line=new ArrayList<ViewablePixel>();
        for (int i = 1; i <= range+1; i++) {
            Coordinate c = new Coordinate(playerCo.getX() + i * signumX, playerCo.getY() + i * signumY);
            for(GameCharacter character:gameCharacters){
                if(character.getCoordinate().equals(c)){
                    targets.add((Vulnerable)character);
                    break;
                }
            }
        }
        return targets;
    }
}
