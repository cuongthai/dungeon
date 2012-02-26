/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills;

import com.rmit.sea.dungeon.resources.Constant;
import java.io.Serializable;

/**
 *
 * @author thailycuong1202
 */
public abstract class Skill implements Serializable {

    private int skillPoints;

    public Skill() {
        skillPoints = 0;

    }
    public Skill(int skillPoints) {
        this.skillPoints = skillPoints;

    }

    public abstract String getSkillDescription();

    public abstract String getSkillName();

    public int getCurrentLevel() {
        int i = 0;
        for (i = 0; i < Constant.MAX_SKILL_LEVEL; i++) {
            if (getSkillPoints() < getNextSkillPointsFor(i + 1)) {
                return i;
            }
        }
        return i;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void increaseSkillPoints() {
        skillPoints++;
    }

    public abstract int getNextSkillPointsFor(int level);

    public abstract int getMpCost(int level);

    public void setSkillPoints(int point){
        this.skillPoints=point;
    }

}
