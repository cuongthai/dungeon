/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.ComputerDetailInfo;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.model.lineofsight.SightActivationDistance;
import com.rmit.sea.gameengine.item.factory.EasyMonsterItemFactory;
import com.rmit.sea.gameengine.item.factory.HardMonsterItemFactory;
import com.rmit.sea.gameengine.item.factory.MediumMonsterItemFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;

/**
 *
 * @author gia
 */
public class Orc extends Monster {

    public Orc(Coordinate coordinate, String difficult, int level) {
        super(coordinate, difficult, level);
        setEquipSkill(new BasicAttackSkill());

    }

    @Override
    public String getMonsterNameType() {
        return "Orc";
    }

    @Override
    public void setupDifficulty(String difficulty, int level) {
        if (getDifficulty().equals(Constant.EASY)) {

            setDistance(new SightActivationDistance(5));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, level / 2));
            setComputerDetailInfo(new ComputerDetailInfo(level <= 10 ? 20 : level * 2, 0));
            setAi(new EasyAIAlgorithm());
            setItemFactory(new EasyMonsterItemFactory());
        } else if (getDifficulty().equals(Constant.MEDIUM)) {

            setDistance(new SightActivationDistance(5));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, level));
            setComputerDetailInfo(new ComputerDetailInfo(level <= 10 ? 20 : level * 2, 0));
            setAi(new MediumAIAlgorithm());
            setItemFactory(new MediumMonsterItemFactory());
        } else if (getDifficulty().equals(Constant.HARD)) {
            setDistance(new SightActivationDistance(10));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, level * 2));
            setComputerDetailInfo(new ComputerDetailInfo(level <= 10 ? 40 : level * 4, 0));
            setAi(new MediumAIAlgorithm());
            setItemFactory(new HardMonsterItemFactory());
        }
    }
}
