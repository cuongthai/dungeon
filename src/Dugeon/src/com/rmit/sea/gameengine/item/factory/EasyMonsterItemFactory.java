/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item.factory;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.item.Arrow;
import com.rmit.sea.gameengine.item.Bow;
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
 * the loot drop of weak(green) monster
 * @author gia
 */
public class EasyMonsterItemFactory implements ItemFactory {

    @Override
    public Item createItem(Coordinate coordinate, int level) {
        Random r = new Random();
        int randomInt = r.nextInt(45);
        level=Math.max(0, level-5);
        if (r.nextInt(100)<Constant.EASY_LOOT_DROP_PERCENTAGE) {
            if (randomInt < 1) {
                return SkillScrollFactory.createSkillScroll(coordinate, level);
            } else if (randomInt < 2) {
                return new PowerUpHpPotion(coordinate, level,1);
            } else if (randomInt < 3) {
                return new PowerUpMpPotion(coordinate, level,1);
            } else if (randomInt < 7) {
                return new HpPotion(coordinate, level,1);
            } else if (randomInt < 11) {
                return new MpPotion(coordinate, level,1);
            } else if (randomInt < 12) {
                return new Sword(coordinate, level);
            } else if (randomInt < 13) {
                return new Hammer(coordinate, level);
            } else if (randomInt < 14) {
                return new Bow(coordinate, level);
            } else if (randomInt < 15) {
                return new LeatherArmor(coordinate, level);
            } else if (randomInt < 16) {
                return new MirrorShield(coordinate, level);
            } else if (randomInt < 25) {
                return new Arrow(coordinate, level);
            } else {
                return new Gold(coordinate, level);
            }
        }else{
            return null;
        }
    }
}
