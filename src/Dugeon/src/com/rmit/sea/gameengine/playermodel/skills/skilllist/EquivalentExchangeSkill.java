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

/**
 *
 * @author Magickey
 */
public class EquivalentExchangeSkill extends Skill implements SpecialEffect, GeneralClass, NotRequireTarget {

    @Override
    public String getSkillDescription() {
        return "Heal both side of the battle. Total HP heal is the minimum so that one side got everyone full HP.";
    }

    @Override
    public String getSkillName() {
        return "Equivalent Exchange";
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        return 0;
    }

    @Override
    public int getMpCost(int level) {
        return 300;
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        int enemySideTotalHp = 0;
        int playerSideTotalHp = 0;

        Player p = (Player) user;
        playerSideTotalHp = p.getCharacterDetailInfo().getMaxHp() - p.getCharacterDetailInfo().getHp();

        for (GameCharacter c : gameCharacters) {
            if (!(c instanceof Player)) {
                enemySideTotalHp += c.getCharacterDetailInfo().getMaxHp() - c.getCharacterDetailInfo().getHp();
            }
        }

        int hpHeal = enemySideTotalHp > playerSideTotalHp ? playerSideTotalHp : enemySideTotalHp;

        p.getCharacterDetailInfo().increaseHp(hpHeal);
        int i = 0;
        while (hpHeal > 0 && i < gameCharacters.size()) {
            GameCharacter c = gameCharacters.get(i);
            if (!(c instanceof Player)) {
                int need = c.getCharacterDetailInfo().getMaxHp() - c.getCharacterDetailInfo().getHp();
                int heal = hpHeal > need ? need : hpHeal;
                hpHeal -= heal;
                c.getCharacterDetailInfo().increaseHp(heal);
                i++;
            }
        }

    }
}
