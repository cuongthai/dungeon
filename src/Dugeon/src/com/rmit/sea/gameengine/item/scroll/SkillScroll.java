/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item.scroll;

import com.rmit.sea.dungeon.resources.CharacterResources;
import com.rmit.sea.dungeon.resources.ColorResources;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.iteminterface.Learnable;
import com.rmit.sea.gameengine.iteminterface.Valuable;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import java.awt.Color;

/**
 *
 * @author hoanggia
 */
public class SkillScroll extends Item implements Learnable,Valuable {

    private Skill s;
    private int value;
    public SkillScroll(Coordinate coordinate, Skill skill,int level) {
        super(coordinate);
        s = skill;
        value=(level/10+1)*100;
    }

    @Override
    public Skill getSkillToLearn() {
        return s;
    }

    @Override
    public String getDescription() {
        return "Scroll contains instruction to learn the skill: " + getSkillToLearn().getSkillName();
    }

    @Override
    public String getName() {
        return "Skill Scroll: " + getSkillToLearn().getSkillName();
    }

    @Override
    public int getValue() {
        return value;
    }
}
