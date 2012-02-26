/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item.factory;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.item.Bow;
import com.rmit.sea.gameengine.item.ChainMail;
import com.rmit.sea.gameengine.item.Gold;
import com.rmit.sea.gameengine.item.Hammer;
import com.rmit.sea.gameengine.item.HpPotion;
import com.rmit.sea.gameengine.item.Item;
import com.rmit.sea.gameengine.item.LeatherArmor;
import com.rmit.sea.gameengine.item.MirrorShield;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.item.Sword;
import com.rmit.sea.gameengine.item.scroll.SkillScrollFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 * loot drop of normal monster
 * @author gia
 */
public class MediumMonsterItemFactory implements ItemFactory {

    @Override
    public Item createItem(Coordinate coordinate, int level) {
        Random r = new Random();
        int randomInt = r.nextInt(45);
        if (r.nextInt(100) < Constant.MEDIUM_LOOT_DROP_PERCENTAGE) {
            if (randomInt < 3) {
                return SkillScrollFactory.createSkillScroll(coordinate, level);
            } else if (randomInt < 4) {
                return new PowerUpHpPotion(coordinate, level,1);
            } else if (randomInt < 5) {
                return new PowerUpMpPotion(coordinate, level,1);
            } else if (randomInt < 10) {
                return new HpPotion(coordinate, level,1);
            } else if (randomInt < 15) {
                return new MpPotion(coordinate, level,1);
            } else if (randomInt < 18) {
                return new Sword(coordinate, level);
            } else if (randomInt < 20) {
                return new Hammer(coordinate, level);
            } else if (randomInt < 23) {
                return new Bow(coordinate, level);
            } else if (randomInt < 30) {
                return new Arrow(coordinate, level);
            } else if (randomInt < 32) {
                return new LeatherArmor(coordinate, level);
            } else if (randomInt < 35) {
                return new MirrorShield(coordinate, level);
            } else {
                return new Gold(coordinate, level);
            }
        } else {
            return null;
        }
    }
}
