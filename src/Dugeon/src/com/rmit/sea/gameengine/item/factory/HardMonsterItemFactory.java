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
import com.rmit.sea.gameengine.item.Katana;
import com.rmit.sea.gameengine.item.LeatherArmor;
import com.rmit.sea.gameengine.item.Mallet;
import com.rmit.sea.gameengine.item.MirrorShield;
import com.rmit.sea.gameengine.item.MpPotion;
import com.rmit.sea.gameengine.item.PowerUpHpPotion;
import com.rmit.sea.gameengine.item.PowerUpMpPotion;
import com.rmit.sea.gameengine.item.Sword;
import com.rmit.sea.gameengine.item.WallShield;
import com.rmit.sea.gameengine.item.scroll.SkillScrollFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import java.util.Random;

/**
 *  this will determine the loot drop of boss(red) monster
 * @author gia
 */
public class HardMonsterItemFactory implements ItemFactory {

    @Override
    public Item createItem(Coordinate coordinate, int level) {
        Random r = new Random();
        int randomInt = r.nextInt(120);
        level=level+5;
        if (r.nextInt(100) < Constant.HARD_LOOT_DROP_PERCENTAGE) {
            if (randomInt < 10) {
                return SkillScrollFactory.createSkillScroll(coordinate, level);
            } else if (randomInt < 15) {
                return new PowerUpHpPotion(coordinate, level,1);
            } else if (randomInt < 20) {
                return new PowerUpMpPotion(coordinate, level,1);
            } else if (randomInt < 25) {
                return new HpPotion(coordinate, level,1);
            } else if (randomInt < 35) {
                return new MpPotion(coordinate, level,1);
            } else if (randomInt < 45) {
                return new Sword(coordinate, level);
            } else if (randomInt < 55) {
                return new Hammer(coordinate, level);
            } else if (randomInt < 65) {
                return new Bow(coordinate, level);
            } else if (randomInt < 75) {
                return new Arrow(coordinate, level);
            } else if (randomInt < 85) {
                return new LeatherArmor(coordinate, level);
            } else if (randomInt < 95) {
                return new MirrorShield(coordinate, level);
            } else if (randomInt < 100) {
                return new ChainMail(coordinate, level);
            } else if (randomInt < 105) {
                return new WallShield(coordinate, level);
            } else if (randomInt < 105) {
                return new Katana(coordinate, level);
            } else if (randomInt < 105) {
                return new Mallet(coordinate, level);
            } else {
                return new Gold(coordinate, level);
            }
        } else {
            return null;
        }
    }
}
