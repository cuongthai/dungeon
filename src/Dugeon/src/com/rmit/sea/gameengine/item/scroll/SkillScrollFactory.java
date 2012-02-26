/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.item.scroll;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.Skill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicShootingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BlessingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.CatastropheSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.CureSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.DashSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.DigAHoleSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.EarthquakeSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.EquivalentExchangeSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ExplosiveArrowSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerBattingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.HammerJamSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.LeapSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ReverseDestinySkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.UnsheathSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SoulThrustingSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordDanceSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordRainSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.KnightThrustSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.PraySkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.ShockwaveSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordRushSkill;
import java.util.Random;

/**
 *
 * @author hoanggia
 */
public class SkillScrollFactory {

    /**
     * this method will get the skill scroll base on the current level
     * the skill will be generate base on the leve
     * @param co where to put the scroll
     * @param level ground level
     *
     * @return
     */
    public static SkillScroll createSkillScroll(Coordinate co, int level) {
        return new SkillScroll(co, getSkillForLevel(level), level);

    }

    /**
     * base on the ground level, get the right skill for the scroll
     * @param level
     * @return
     */
    private static Skill getSkillForLevel(int level) {
        Random r = new Random();
        if (level <= 10) {
            switch (r.nextInt(5)) {
                case 0:
                    return new SwordAttackSkill();
                case 1:
                    return new HammerAttackSkill();
                case 2:
                    return new BasicShootingSkill();
                case 3:
                    return new DigAHoleSkill();
                default:
                    return new BasicAttackSkill();
            }
        } else if (level <= 20) {
            switch (r.nextInt(8)) {
                case 0:
                    return new SwordDanceSkill();
                case 1:
                    return new HammerJamSkill();
                case 2:
                    return new KnightThrustSkill();
                case 3:
                    return new ReverseDestinySkill();
                case 4:
                    return new ShockwaveSkill();
                case 5:
                    return new DashSkill();
                case 6:
                    return new PraySkill();
                default:
                    return getSkillForLevel(1);
            }
        } else if (level <= 40) {
            switch (r.nextInt(9)) {
                case 0:
                    return new SwordRainSkill();
                case 1:
                    return new UnsheathSkill();
                case 2:
                    return new HammerBattingSkill();
                case 3:
                    return new EarthquakeSkill();
                case 4:
                    return new LeapSkill();
                case 5:
                    return new ExplosiveArrowSkill();
                case 6:
                    return new SwordRushSkill();
                case 7:
                    return new CureSkill();
                default:
                    return getSkillForLevel(11);
            }
        } else if (level <= 60) {
            switch (r.nextInt(4)) {
                case 0:
                    return new SoulThrustingSkill();
                case 1:
                    return new EquivalentExchangeSkill();
                case 2:
                    return new BlessingSkill();
                default:
                    return getSkillForLevel(31);
            }
        } else if (level == 99) {
            switch (r.nextInt(5)) {
                case 0:
                    return new CatastropheSkill();
                default:
                    return getSkillForLevel(98);
            }
        } else {
            return getSkillForLevel(98);
        }

    }
}
