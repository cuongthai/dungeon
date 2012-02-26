/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rmit.sea.gameengine.charactermodel.monster;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.ComputerDetailInfo;
import com.rmit.sea.gameengine.charactermodel.Damage;
import com.rmit.sea.gameengine.item.factory.EasyMonsterItemFactory;
import com.rmit.sea.gameengine.item.factory.HardMonsterItemFactory;
import com.rmit.sea.gameengine.item.factory.MediumMonsterItemFactory;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.lineofsight.HearingActivationDistance;
import com.rmit.sea.gameengine.playermodel.skills.skilllist.BasicAttackSkill;

/**
 *
 * @author gia
 */
public class Bat extends Monster{
    public Bat(Coordinate coordinate, String difficult, int level){
        super(coordinate, difficult,level);
        setEquipSkill(new BasicAttackSkill());
    }

    @Override
    public String getMonsterNameType() {
        return "Bat";
    }

    @Override
    public void setupDifficulty(String difficulty,int level) {

        if (getDifficulty().equals(Constant.EASY)) {
            setDistance(new HearingActivationDistance(3));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, level));
            setComputerDetailInfo(new ComputerDetailInfo(level<=10?10:level, 0));
            setAi(new EasyAIAlgorithm());
            setItemFactory(new EasyMonsterItemFactory());
        } else if (getDifficulty().equals(Constant.MEDIUM)) {

            setDistance(new HearingActivationDistance(5));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, (int)(level*1.5)));
            setComputerDetailInfo(new ComputerDetailInfo(level<=10?10:level, 0));
            setAi(new MediumAIAlgorithm());
            setItemFactory(new MediumMonsterItemFactory());
        } else if (getDifficulty().equals(Constant.HARD)) {
            setDistance(new HearingActivationDistance(10));
            setDamage(new Damage(Constant.PHYSICAL_ELEMENT, level*3));
            setComputerDetailInfo(new ComputerDetailInfo(level<=10?20:level*2, 0));
            setAi(new MediumAIAlgorithm());
            setItemFactory(new HardMonsterItemFactory());
        }
    }



}
