/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.playermodel.skills.skilllist;

import com.rmit.sea.gameengine.charactermodel.GameCharacter;
import com.rmit.sea.gameengine.charactermodel.characterinterface.Attackable;
import com.rmit.sea.gameengine.charactermodel.player.Player;
import com.rmit.sea.gameengine.item.factory.HardMonsterItemFactory;
import com.rmit.sea.gameengine.item.factory.ItemFactory;
import com.rmit.sea.gameengine.item.factory.MapItemFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.GeneralClass;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.NotRequireTarget;
import com.rmit.sea.gameengine.playermodel.skills.skillinterface.SpecialEffect;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Magickey
 */
public class PraySkill extends Skill implements SpecialEffect, NotRequireTarget, GeneralClass {

    private int numberOfPray = 0;

    @Override
    public String getSkillDescription() {
        return "Pray to the God to receive something fun. It may be a new title, a new item, a litle bit of healing or just thunder from above...";
    }

    @Override
    public String getSkillName() {
        return "Pray";
    }

    @Override
    public int getNextSkillPointsFor(int level) {
        return 0;
    }

    @Override
    public int getMpCost(int level) {
        return 100;
    }

    @Override
    public void executeSpecialEffect(Attackable user, int level, Coordinate executeCoordinate, List<GameCharacter> gameCharacters, Map<Coordinate, ViewablePixel> fullMap, Direction direction) {
        Player p = (Player) user;
        if (!p.isAcquireNewTitle()) {
            Random r = new Random();

            int choice = r.nextInt(100);
            if (choice < 5) {
                p.getCharacterDetailInfo().increaseHp(p.getCharacterDetailInfo().getMaxHp());
            } else if (choice < 30) {
                p.getCharacterDetailInfo().increaseHp((p.getLevel() / 10) * 10 * 5);
            } else if (choice < 35) {
                ItemFactory factory = new HardMonsterItemFactory();
                p.getInventory().addItem(factory.createItem(executeCoordinate, p.getLevel()));
            } else if (choice < 50) {
                ItemFactory factory = new MapItemFactory();
                p.getInventory().addItem(factory.createItem(executeCoordinate, p.getLevel()));
            } else {
                p.getCharacterDetailInfo().increaseHp(-p.getCharacterDetailInfo().getHp() / 2);
            }
        }
    }
}
