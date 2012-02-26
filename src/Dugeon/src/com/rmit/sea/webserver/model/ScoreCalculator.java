/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.webserver.model;

import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import java.util.List;

/**
 *
 * @author Steve
 */
public class ScoreCalculator {


    public int getScore(Player player, int currentGroundLevel) {
        int groundScore = getGroundScore(currentGroundLevel);
        int numSkillScore = getNumberOfSkillScore(player.getSkillManager().getAvailableSkills());

        int mpScore = getMPScore(player.getCharacterDetailInfo().getMp());
        int currentTrainSkillPointScore = getCurrentSkillPointScore(player.getSkillManager().getCurrentTrainSkill());
        int currentMoney = getCurrentMoney(player.getInventory().getCurrentGold());
        int currentInventory = getInventoryScore(player.getInventory().getItems());
        return groundScore + numSkillScore +  mpScore + currentTrainSkillPointScore + currentMoney + currentInventory;
    }



    private int getGroundScore(int currentGroundLevel) {
        if (currentGroundLevel < 5) {
            return currentGroundLevel * 1000;
        } else if (currentGroundLevel < 7) {
            return currentGroundLevel * 2000;
        } else if (currentGroundLevel < 10) {
            return currentGroundLevel * 3000;
        } else if (currentGroundLevel < 20) {
            return currentGroundLevel * 7000;
        } else {
            return currentGroundLevel * 10000;
        }
    }

    private int getNumberOfSkillScore(List<Skill> availableSkills) {
        int score = 0;
        for (Skill skill : availableSkills) {
            score += skill.getSkillPoints() * 30;
        }
        score += availableSkills.size() * 100;
        return score;
    }

    private int getInventoryScore(List<Item> items) {
        return items.size() * 40;
    }

    private int getCurrentMoney(int currentGold) {
        return currentGold * 15;
    }

    private int getCurrentSkillPointScore(Skill currentTrainSkill) {
        return currentTrainSkill.getSkillPoints() * 80;
    }

    private int getMPScore(int mp) {
        return mp * 50;
    }
}
