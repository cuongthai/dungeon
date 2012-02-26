package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.model.lineofsight.SightActivationDistance;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.SwordAttackSkill;
import java.util.Random;

public class MonsterFactory {

    public static Monster createMonster(int level, Coordinate co) {
        Random r = new Random();
        int percent = r.nextInt(2);

        return percent==0?new Orc(co.clone(), generateDifficulty(level), level):new Bat(co.clone(), generateDifficulty(level), level);
    }

    private static String generateDifficulty(int level) {
        Random r = new Random();
        int percent = r.nextInt(100);
        if (percent < 70) {
            return Constant.MEDIUM;
        } else if (percent < 80 + level / 10) {
            return Constant.HARD;
        } else {
            return Constant.EASY;
        }
    }
}
